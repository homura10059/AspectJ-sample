package com.sample.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.slf4j.Log4jLogger;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.Optional;

public class SampleService {

        private static Logger logger;

        public SampleService() {
                String name = "Default";
                logger = new Log4jLogger(LogManager.getContext().getLogger(name), name);
        }

        public String get(Object o){
                if (o instanceof String){
                        return o.toString();
                }else {
                        o = "aaaa";
                        logger.info("ああああああ");
                        return null;
                }
        }

        public Optional<String> getOptional(Object o){
                if (o instanceof String){
                        return Optional.of(o.toString());
                }
                logger.info("ああああああ");
                return Optional.empty();
        }

        public Optional<String> getException(Object o){
                Objects.requireNonNull(o);
                if (o instanceof String){
                        return Optional.of(o.toString());
                }
                return Optional.empty();
        }
}
