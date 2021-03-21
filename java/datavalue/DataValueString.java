package pl.wiktorekx.bt.datavalue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DataValueString {
    private final DataValueBytes dataValueBytes;
    private Charset charset;

    public DataValueString(String value) {
        this(value, StandardCharsets.UTF_8);
    }

    public DataValueString(String value, Charset charset) {
        this.dataValueBytes = new DataValueBytes(value.getBytes(charset));
        this.charset = charset;
    }

    public DataValueString(DataValueBytes dataValueBytes) {
        this.dataValueBytes = dataValueBytes;
    }

    public String getValue() {
        return new String(this.dataValueBytes.getBytes(), charset);
    }

    public DataValueBytes getDataValueBytes() {
        return dataValueBytes;
    }
}
