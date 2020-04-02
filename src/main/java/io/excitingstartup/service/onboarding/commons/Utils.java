package io.excitingstartup.service.onboarding.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author Arun Vishnu
 */

@Slf4j
public class Utils {

    public static void copyObject( Object source, Object destination ) {
        Objects.requireNonNull( source );
        Objects.requireNonNull( destination );

        //Copy properties from source to destination
        try {
            BeanUtils.copyProperties( destination, source );
        } catch (IllegalAccessException e) {
            log.error( "Unable to copy bean properties. Exception occurred." );
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error( "Unable to copy bean properties due to exception : " );
            e.printStackTrace();
        }
    }

}
