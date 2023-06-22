package ru.sber.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.sber.exceptions.EmptyCollectionArgException;
import ru.sber.exceptions.EmptyStringArgException;
import ru.sber.exceptions.NullArgException;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Аспект, отвечающий за проверку аргументов методов аннотации @NotEmpty
 */
@Aspect
public class NotEmptyAspect {

    private Logger logger = Logger.getLogger(NotEmptyAspect.class.getName());

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Before("@annotation(NotEmpty)")
    public void argsCheck(JoinPoint joinPoint) throws Throwable{
        logger.info("Logging Aspect: Calling the intercepted method");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                logger.severe("Был передан null аргумент");
                throw new NullArgException("Ошибка! Был передан null аргумент");
            } else if (arg instanceof String) {
                if (((String) arg).isEmpty()){
                    logger.severe("Была передана в качестве аргумента пустая строка");
                    throw new EmptyStringArgException("Ошибка! Была передана в качестве аргумента пустая строка");
                }
            } else if (arg instanceof Collection<?>) {
                if (((Collection<?>) arg).isEmpty()){
                    logger.severe("Была передана в качестве аргумента пустая коллекция");
                    throw new EmptyCollectionArgException("Ошибка! Была передана в качестве аргумента пустая коллекция");
                }
            }
        }
    }

}
