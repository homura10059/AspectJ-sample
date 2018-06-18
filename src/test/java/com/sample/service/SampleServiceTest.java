package com.sample.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class SampleServiceTest {

        private SampleService sut;

        @Before
        public void setUp() throws Exception {
                sut = new SampleService();
        }

        @Test
        public void test_引数がNULL() {
                assertNull(sut.get(null));
        }

        @Test
        public void test_戻り値が英数() {
                assertThat(sut.get("a1"), is("a1"));
        }

        @Test
        public void test_戻り値がマルチバイト() {
                assertThat(sut.get("ああああああ"), is("ああああああ"));
        }

        @Test
        public void test_戻り値がOptionalでマルチバイト() {
                assertThat(sut.getOptional("ああああああ"), is(Optional.of("ああああああ")));
        }

        @Test
        public void test_戻り値がOptional_Empty() {
                assertThat(sut.getOptional(1), is(Optional.empty()));
        }

        @Test
        public void test_戻り値がException() {
                try {
                        sut.getException(null);
                } catch (Exception e) {
                        assertTrue(e instanceof NullPointerException);
                }
        }

}