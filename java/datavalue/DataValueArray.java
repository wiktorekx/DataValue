package pl.wiktorekx.bt.datavalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataValueArray extends DataValue {
    private List<DataValue> list;

    public DataValueArray(List<DataValue> list) {
        this.list = list;
    }

    protected DataValueArray(DataInput dataInput, Info info) throws IOException {
        super(dataInput, info);
    }

    @Override
    public void send(DataOutput dataOutput) throws IOException {
        sendInfo(dataOutput, Action.ARRAY, list.size());
        for(DataValue dataValue : list){
            dataValue.send(dataOutput);
        }
    }

    @Override
    public void read(DataInput dataInput, Info info) throws IOException {
        this.list = new ArrayList<>();
        for(int i = 0; i < info.getSize(); i++){
            list.add(readObject(dataInput));
        }
    }

    public List<DataValue> getList() {
        return list;
    }
}
