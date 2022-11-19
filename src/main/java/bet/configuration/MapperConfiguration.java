/*
 * ***********************************************************************************
 *  * Copyright BMC Software Inc.                                                      *
 *  *                                                                                  *
 *  *                                                                                  *
 *  *  `.-.`                                                                           *
 *  *  +oooo/:.                                                                        *
 *  *  `ooo-/+oo+:.`                                                                   *
 *  *  `ooo  `.:+ooo/-`           bmmm`                                                *
 *  *  `ooo.     `-/+oo+:.`       bmmm`                                                *
 *  *   -+oo/-`     `.:+oo+.      bmmm`                                                *
 *  *    `-/+oo+:.`  `-/ooo-      bmmm:+cccb/.   +mmm:mmmm+mm/mmmmm+-    `:+cccc+:`    *
 *  *       `.:+oo+/+oo+/-`       bmmmmbcbmmmb/  cmmmmbbmmmmmmbbmmmm/  :bmmbccbmmb-    *
 *  *         `-+oooooo/.`        bmmm:` `.cdmm: cmmm:``-mmmm-``-mmmb -mmdo`  .+oo/    *
 *  *     `.:+ooo/:.-/+oo+:`      bmmm`    .mmmo cmmm`   bmmm`   mmmb ommm.            *
 *  *   `:+oo+/-`     .:ooo:      bmmm`    /dmm+ cmmm`   bmmm`   mmmb /mmd/    ---.    *
 *  *   /oo/.`     .:/+oo/:`      bmmmb+//cmmmb` cmmm`   bmmm`   mmmb `cmmmo//ommm/    *
 *  *  `ooo    `-/+oo+/-`         cmmmcbmmmmc/`  cmmm`   cmmb`   bmmc  `:cbmmmmbc:     *
 *  *  `ooo .-/ooo+:.`            ```` `...``    ````    ````    ````     `....`       *
 *  *  `ooo+ooo/-`                                                                     *
 *  *  -+++:.`                                                                         *
 *  *                                                                                  *
 *  *                                                                                  *
 *  * This source code is property of BMC Software Inc.,                               *
 *  * covered by Copyright. All rights reserved.                                       *
 *  *                                                                                  *
 *  ***********************************************************************************
 */
package bet.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.time.LocalDateTime;

public class MapperConfiguration {

    private JacksonEncoder encoder;
    private JacksonDecoder decoder;

    public MapperConfiguration() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        objectMapper.registerModule(simpleModule);

        encoder = new JacksonEncoder(objectMapper);
        decoder = new JacksonDecoder(objectMapper);
    }

    public JacksonEncoder getEncoder() {
        return encoder;
    }

    public JacksonDecoder getDecoder() {
        return decoder;
    }
}
