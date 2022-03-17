package com.coen390.hvacinator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UnitListAdapter extends ArrayAdapter<Unit> {
    private Context mContext;
    private ArrayList<Unit> units = new ArrayList<>();

    public UnitListAdapter(@NonNull Context context, ArrayList<Unit> units) {
        super(context, 0, units);
        mContext = context;
        this.units = units;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.unit_list_item, parent, false);
        }

        Unit unit = units.get(position);
        TextView unitIDText = listItem.findViewById(R.id.ItemUnitIDText);
        TextView unitNicknameText = listItem.findViewById(R.id.ItemUnitNicknameText);
        TextView targetTemperatureText = listItem.findViewById(R.id.ItemTargetTemperatureText);
        unitIDText.setText(unit.ID);
        unitNicknameText.setText(unit.nickname);
        targetTemperatureText.setText(unit.targetTemperature.toString() + " °C");
        return listItem;
    }
}
