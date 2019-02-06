package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class AnalyzeAdapter extends RecyclerView.Adapter<AnalyzeAdapter.ViewHolder> {
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView analyze_unit;
        public TextView analyze_value;


        public ViewHolder(View analyzeItemView) {
            super(analyzeItemView);

            analyze_unit = analyzeItemView.findViewById(R.id.analyze_unit);
            analyze_value = analyzeItemView.findViewById(R.id.analyze_value);
        }
    }
    private List<ScalarModel> scalars;

    public AnalyzeAdapter(List<ScalarModel> scalars) {
        this.scalars = scalars;
    }

    @Override
    public AnalyzeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View analyze_item = inflater.inflate(R.layout.analyze_item, parent, false);

        // Return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(analyze_item);

        //Create onClick
        //TODO OnClick goes to detailed view of achievement


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ScalarModel scalar = scalars.get(position);

        // Set item views based on your views and data model
        TextView analyzeItemUnit = viewHolder.analyze_unit;
        TextView analyzeItemValue = viewHolder.analyze_value;


        //TODO How is this added generically?
            analyzeItemUnit.setText(scalar.getUnit());
            analyzeItemValue.setText(scalar.getValue());

    }

    @Override
    public int getItemCount() {
        return scalars.size();
    }

}
