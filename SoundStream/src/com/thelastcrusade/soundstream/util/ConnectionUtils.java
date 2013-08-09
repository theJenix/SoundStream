/*
 * Copyright 2013 The Last Crusade ContactLastCrusade@gmail.com
 * 
 * This file is part of SoundStream.
 * 
 * SoundStream is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SoundStream is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SoundStream.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thelastcrusade.soundstream.util;

import android.content.Context;
import android.content.Intent;

public class ConnectionUtils {

    public static final String ACTION_CONNECTED    = "com.thelastcrusade.soundstream.action.CONNECTED";
    public static final String ACTION_DISCONNECTED = "com.thelastcrusade.soundstream.action.DISCONNECTED";

    /**
     * Notify the UI or any listeners that the socket is connected 
     * 
     */
    public static void notifyConnected(Context mmContext) {
        Intent intent = new Intent();
        intent.setAction(ConnectionUtils.ACTION_CONNECTED);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        mmContext.sendBroadcast(intent);
    }

}