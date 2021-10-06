package com.mmb.setting.entity

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.mmb.setting_proto.Setting
import java.io.InputStream
import java.io.OutputStream

object SettingSerializer : Serializer<Setting> {
    override val defaultValue: Setting = Setting.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): Setting {
        try {
            return Setting.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Setting, output: OutputStream) = t.writeTo(output)

    const val SETTING_NAME = "setting"
    const val DATA_STORE_FILE_NAME = "setting.pb"
    const val SORT_ORDER_KEY = "sort_order"
}