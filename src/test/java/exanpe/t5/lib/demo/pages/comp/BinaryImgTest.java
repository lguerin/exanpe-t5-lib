//
// Copyright 2011 EXANPE <exanpe@gmail.com>
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package exanpe.t5.lib.demo.pages.comp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

public class BinaryImgTest
{

    private static final byte[] ICON = new byte[]
    { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 16, 0, 0, 0, 16, 8, 6, 0, 0, 0, 31, -13, -1, 97, 0, 0, 0, 4, 103, 65, 77, 65, 0,
            0, -81, -56, 55, 5, -118, -23, 0, 0, 0, 25, 116, 69, 88, 116, 83, 111, 102, 116, 119, 97, 114, 101, 0, 65, 100, 111, 98, 101, 32, 73, 109, 97, 103,
            101, 82, 101, 97, 100, 121, 113, -55, 101, 60, 0, 0, 2, -97, 73, 68, 65, 84, 56, -53, -91, -109, -21, 75, 83, 97, 28, -57, -3, 59, 118, -50, 118,
            108, 3, 9, 100, 68, 33, -126, -124, 80, 123, 21, 36, 18, 59, -102, 13, -59, -68, 44, 75, -45, -35, -67, -46, 54, 99, -40, 76, -117, 50, 114, 94,
            -58, 72, 41, 45, -77, -44, 106, 115, 78, 109, -22, -44, -26, -42, -108, 50, 113, -39, 81, 66, -52, -67, -23, 66, -75, 97, 116, -79, 111, -25, -20,
            -59, 76, 18, 35, 122, -32, 11, 15, 15, -49, -25, -13, 123, -82, 113, 0, -30, -2, 39, 127, 12, 20, -8, 14, -119, 114, -89, 15, -22, -77, 61, 41, 76,
            -58, -29, -3, 97, -23, -120, 56, 44, 117, 37, 50, 82, 103, -94, 62, -35, -66, 87, -76, -85, 32, -49, -101, 74, -53, 60, -55, 33, -99, 71, -122,
            -101, 65, 11, -6, -106, -69, -94, -23, 92, 108, 70, -119, -21, 24, 36, -67, 84, 72, -46, 67, -47, 59, 10, -40, -86, 116, -26, 120, 82, -28, -22,
            -100, 17, -50, -43, 126, -40, 94, 94, -125, 105, -82, 50, 26, -82, -17, 88, -19, 67, -29, 76, 21, 14, -40, -8, -111, 100, 27, -97, -34, 38, -56,
            -15, -92, 8, 51, -35, 73, -21, 28, -52, 77, -84, 9, -108, -95, -62, 95, 2, -51, -52, 25, -24, -40, -108, -77, -87, -10, -99, -123, -3, -11, 61, 92,
            -100, -86, -128, -40, 66, -82, -117, -81, -109, -62, -104, 64, -26, 78, 50, -88, -58, -78, -94, -107, 57, -104, 3, 85, -34, 83, 80, 76, 23, 66, 49,
            85, 0, -11, 84, 33, -36, 107, -125, 48, 120, -107, 112, -80, -110, -36, -98, 35, 72, -72, 66, 26, 98, -126, -116, 17, 49, -45, 25, 108, -122, 53,
            -40, -124, 10, 95, 49, -108, 79, -28, 44, -104, 15, -27, 36, 27, 79, 62, -58, -33, -72, -64, -75, 80, 100, 13, 109, -49, 27, 97, -101, 107, 68,
            124, 61, -55, -60, 4, 71, -19, 9, 27, 15, 86, 110, -93, 54, -96, -127, -42, 91, -60, -82, 100, 0, -117, 31, -26, -95, -102, 40, -60, -40, -38, 80,
            20, -2, -79, -7, 29, 109, -49, 46, -63, 48, 81, -116, -66, 96, 39, 4, 70, 98, 35, 38, -112, -36, -89, 54, -6, -105, -69, 97, -12, -85, 80, -21,
            -41, -30, -45, -41, -113, 81, -24, -3, -105, -73, 49, -40, 50, 91, 15, -75, 43, 27, 122, -9, 105, -12, 7, 59, 32, -88, -7, 93, -48, 67, 49, 55,
            -26, -101, -48, -66, 112, 25, -70, 73, 57, -52, -66, 106, 68, -66, 125, -114, -62, -101, 63, 55, 97, 121, 122, 1, 101, -50, 44, 104, 88, 65, 75,
            -96, 14, -19, 94, 51, -88, 42, 98, 107, 11, -87, -73, 4, 6, -7, 64, 26, -20, 43, 119, 81, 61, 33, -121, -38, 125, 18, 117, -45, -27, 88, 122, -73,
            -128, -74, -39, 6, -108, 14, 30, -121, -62, 113, 2, 58, 103, 14, -20, -81, -70, -111, 110, 61, 12, -86, -110, -40, 58, -60, 100, 43, 95, -72, -113,
            -67, 26, -77, 71, -125, -121, -52, 29, 84, -114, -26, 65, 59, -100, 3, -43, -112, 12, 74, 7, 23, 14, -50, -58, -93, -91, 46, 24, -121, -118, 33,
            80, -13, -42, 41, 53, 33, -36, -10, -112, 18, -101, 72, 58, -66, -127, -120, -104, -36, 101, 112, -80, -110, -42, -128, 25, -6, -47, 34, -100, 27,
            -106, -93, -107, -35, -126, -99, -123, -11, -50, 34, -16, 75, 121, 17, 22, -90, 119, 124, -54, 123, 26, 72, -102, 50, 17, 33, 105, -121, 4, -19,
            126, 51, 122, 95, 88, -47, 59, 111, -123, -59, 107, 66, 90, 75, 42, 4, 10, 94, -120, 82, 17, -12, -82, -97, -119, 58, 79, -118, 40, 3, -95, -89,
            106, 8, 70, -96, -27, -123, 5, 42, 94, -104, -83, -56, -80, -47, 83, -91, -124, -24, -81, -65, -15, 95, -13, 11, 103, -48, -84, -27, 121, -70, -44,
            99, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126 };

    @Property
    @Persist(PersistenceConstants.FLASH)
    private InputStream filestream;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private InputStream bytestream;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private InputStream errorstream;

    @SetupRender
    void init() throws IOException
    {
        filestream = Thread.currentThread().getContextClassLoader().getResourceAsStream("exanpe/t5/lib/demo/img/images.jpg");

        bytestream = new ByteArrayInputStream(ICON);

        errorstream = Thread.currentThread().getContextClassLoader().getResourceAsStream("exanpe/t5/lib/demo/img/images.jpg");
        errorstream.close();
    }
}
