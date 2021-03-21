package pl.wiktorekx.bt.datavalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class DataValue {
    public DataValue() {
    }

    protected DataValue(DataInput dataInput, Info info) throws IOException {
        read(dataInput, info);
    }

    public abstract void send(DataOutput dataOutput) throws IOException;

    public abstract void read(DataInput dataInput, Info info) throws IOException;

    public void sendInfo(DataOutput dataOutput, Action action, int value) throws IOException {
        dataOutput.writeByte(0x21);
        dataOutput.writeByte(action.ordinal());
        dataOutput.writeInt(value);
    }

    public void sendBytes(DataOutput dataOutput, byte[] value) throws IOException {
        sendInfo(dataOutput, Action.DATA, value.length);
        dataOutput.write(value);
    }

    public static DataValue readObject(DataInput dataInput) throws IOException{
        if(dataInput.readByte() == 0x21) {
            Info info = new Info();
            info.action = Action.values()[dataInput.readByte()];
            info.size = dataInput.readInt();
            if (info.action == Action.DATA) {
                return new DataValueBytes(dataInput, info);
            }
            if (info.action == Action.OBJECT) {
                return new DataValueObject(dataInput, info);
            }
            if (info.action == Action.ARRAY) {
                return new DataValueArray(dataInput, info);
            }
        }
        return null;
    }

    public static class Info {
        private Action action;
        private int size;

        public Action getAction() {
            return action;
        }

        public int getSize() {
            return size;
        }
    }

    public enum Action {
        DATA,
        OBJECT,
        ARRAY,
    }
}
