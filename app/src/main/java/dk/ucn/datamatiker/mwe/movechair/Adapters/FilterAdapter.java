package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.FilterModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class FilterAdapter extends ArrayAdapter<FilterModel> {

    private Context context;
    private ArrayList<FilterModel> filterModels;
    private FilterAdapter filterAdapter;
    private boolean isFromView = false;

    public FilterAdapter(Context context, int resource, List<FilterModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.filterModels = (ArrayList<FilterModel>) objects;
        this.filterAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.spinner_activity, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.spinner_title);
            holder.checkBox = (CheckBox) convertView
                    .findViewById(R.id.spinner_activity_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(filterModels.get(position).getName());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.checkBox.setChecked(filterModels.get(position).getSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.checkBox.setVisibility(View.INVISIBLE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
        }
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    filterModels.get(position).setSelected(isChecked);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView textView;
        private CheckBox checkBox;
    }
}
