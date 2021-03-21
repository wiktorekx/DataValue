package pl.wiktorekx.bt.datavalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataValueBytes extends DataValue {
    private byte[] bytes;

    public DataValueBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    protected DataValueBytes(DataInput dataInput, Info info) throws IOException {
        super(dataInput, info);
    }

    @Override
    public void send(DataOutput dataOutput) throws IOException {
        sendBytes(dataOutput, bytes);
    }

    @Override
    public void read(DataInput dataInput, Info info) throws IOException {
        this.bytes = new byte[info.getSize()];
        dataInput.readFully(this.bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }
}
