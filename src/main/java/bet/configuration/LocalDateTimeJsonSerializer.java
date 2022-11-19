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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
    public LocalDateTimeJsonSerializer() {
        super();
    }

    @Override
    public void serialize(LocalDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateTimeFormatter.ISO_DATE_TIME.format(offsetDateTime));
    }
}
