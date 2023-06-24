package com.example.wifiscaner;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<ScanResult> wifiList;
    public static Element[] nets;
    public ListAdapter(Context context, List<ScanResult> wifiList)
    {
        this.context=context;
        this.wifiList=wifiList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//класс, который умеет из содержимого layout-файла создать View-элемент
        nets = new Element[wifiList.size()];
    }

    @Override
    public int getCount() {
        return wifiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        Holder holder;
        View view = convertview;

        if(view==null)
        {
            view = inflater.inflate(R.layout.list_item,null); //метод inflate создает View из layout-файла
            holder = new Holder();
            holder.tvDetails=(TextView) view.findViewById(R.id.txtWifiName);
            view.setTag(holder);
        }
        else
        {
            holder = (Holder) view.getTag();
        }
        holder.tvDetails.setText(wifiList.get(i).SSID);

        String item = wifiList.get(i).toString();
        String [] vector_item = item.split(",");
        String item_essid = vector_item[0];
        String item_bssid = vector_item[1];
        String item_capabilities = vector_item[2];
        String item_level = vector_item[3];
        String item_frequency = vector_item[4];

        String ssid = item_essid.split(":")[1];
        String bssid = item_bssid.split(": ")[1];
        String frequency = item_frequency.split(": ")[1];
        String capabilities = item_capabilities.split(":")[1];
        String level = item_level.split(":")[1];
        String encryption = (item_capabilities.split(": ")[1]);
        int in=0;
        if(encryption.contains("WPA2"))
        {
            int index = encryption.indexOf("WPA2");
            in = encryption.indexOf(']',index);
        }
        else
            if(encryption.contains("WPA"))
                in = encryption.indexOf(']',0);

        encryption=encryption.substring(0,in+1);
        capabilities=capabilities.substring(in+2,capabilities.length());
       int lev = Integer.parseInt(level.trim());
        if(lev>-50)
            level="High";
        else
        if(lev<=-50 && i> -80)
            level="Average";
        else
        if(lev<=-80)
            level="Low";

        nets[i] = new Element(ssid,bssid,frequency, level,capabilities,encryption);

        return view;
    }

    class  Holder{
        TextView tvDetails;
    }
}
