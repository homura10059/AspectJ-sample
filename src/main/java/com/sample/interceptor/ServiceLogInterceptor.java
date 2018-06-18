package com.sample.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.slf4j.Log4jLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class ServiceLogInterceptor {
        private static Logger logger;
        /**
         * nullの代わりにログに出す文字列.
         */
        private static String altNull = "<null>";

        public ServiceLogInterceptor() {
                String name = "Default";
                logger = new Log4jLogger(LogManager.getContext().getLogger(name), name);
        }

        /**
         * メソッド実行前に引数をログを出す.
         * @param jp JoinPoint
         */
        @Before("execution(public * *(..))")
        public void beforeLog(JoinPoint jp) {
                String signature = jp.getSignature().toString();
                String args = joined(jp.getArgs());
                String state = "Before";
                String type = "args";
                logger.info(formatted(signature, state, type, args));
        }

        /**
         * メソッド実行後に引数をログを出す.
         * @param jp JoinPoint
         */
        @After("execution(public * *(..))")
        public void afterLog(JoinPoint jp) {
                String signature = jp.getSignature().toString();
                String args = joined(jp.getArgs());
                String state = "After";
                String type = "args";
                logger.info(formatted(signature, state, type, args));

        }

        /**
         * メソッド実行後に戻り値をログを出す.
         * @param jp JoinPoint
         * @param returnValue 戻り値
         */
        @AfterReturning(value = "execution(public * *(..))", returning = "returnValue")
        public void afterReturningLog(JoinPoint jp, Object returnValue) {
                String signature = jp.getSignature().toString();
                String state = "After";
                String type = "return";
                logger.info(formatted(signature, state, type, returnValue));

        }

        /**
         * メソッド実行後に例外をログを出す.
         * @param jp JoinPoint
         * @param e 例外
         */
        @AfterThrowing(value = "execution(public * *(..))", throwing = "e")
        public void afterThrowingLog(JoinPoint jp, Throwable e) {
                String signature = jp.getSignature().toString();
                String state = "AfterThrowing";
                String type = "throwing";
                logger.info(formatted(signature, state, type), e);

        }

        /**
         * 渡された文字列をあるフォーマットで結合する.
         * @param signature シグネチャ
         * @param state 状態
         * @param type タイプ
         * @return 結合された文字列
         */
        private static String formatted(String signature, String state, String type) {
                return bracketed(signature) + " " + bracketed(state) + " " + type;
        }

        /**
         * 渡された文字列をあるフォーマットで結合する.
         * @param signature シグネチャ
         * @param state 状態
         * @param type タイプ
         * @param args 配列
         * @return 結合された文字列
         */
        private static String formatted(String signature, String state, String type,
                Object... args) {
                return formatted(signature, state, type) + ": " + joined(args);
        }

        /**
         * 文字列に [] をつける.
         * @param string 文字列
         * @return [文字列]
         */
        private static String bracketed(String string) {
                return "[" + string + "]";
        }

        /**
         * オブジェクトの配列を結合して文字列にする.
         * @param args オブジェクトの配列
         * @return 結合された文字列
         */
        private static String joined(Object[] args) {
                if (args == null) {
                        return altNull;
                }

                return Arrays.stream(args).map(ServiceLogInterceptor::converted)
                        .collect(Collectors.joining(", "));
        }

        /***
         * ObjectをStringに変換する.
         * @param o オブジェクト
         * @return String
         */
        private static String converted(Object o) {
                String result;
                try {
                        result = o.toString();
                } catch (Throwable e) {
                        // o.toString() でExceptionを出す行儀の悪いオブジェクトが存在するため
                        result = altNull;
                }
                return result;
        }

}
