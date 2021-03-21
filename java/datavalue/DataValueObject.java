package pl.wiktorekx.bt.datavalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataValueObject extends DataValue {
    private Map<DataValue, DataValue> map;

    public DataValueObject(Map<DataValue, DataValue> map) {
        this.map = map;
    }

    protected DataValueObject(DataInput dataInput, Info info) throws IOException {
        super(dataInput, info);
    }

    @Override
    public void send(DataOutput dataOutput) throws IOException {
        sendInfo(dataOutput, Action.OBJECT, map.size());
        for(Map.Entry<DataValue, DataValue> entry : map.entrySet()){
            entry.getKey().send(dataOutput);
            entry.getValue().send(dataOutput);
        }
    }

    @Override
    public void read(DataInput dataInput, Info info) throws IOException {
        this.map = new HashMap<>();
        for(int i = 0; i < info.getSize(); i++){
            map.put(readObject(dataInput), readObject(dataInput));
        }
    }

    public Map<DataValue, DataValue> getMap() {
        return map;
    }
}
