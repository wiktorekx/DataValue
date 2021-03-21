package pl.wiktorekx.bt.datavalue;

import java.nio.ByteBuffer;

public class DataValueInt {
    private final DataValueBytes dataValueBytes;

    public DataValueInt(int value) {
        dataValueBytes = new DataValueBytes(ByteBuffer.allocate(4).putInt(value).array());
    }

    public DataValueInt(DataValueBytes dataValueBytes) {
        this.dataValueBytes = dataValueBytes;
    }

    public int getValue() {
        return ByteBuffer.wrap(this.dataValueBytes.getBytes()).getInt();
    }

    public DataValueBytes getDataValueBytes() {
        return dataValueBytes;
    }
}
