package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class ParameterVisualizationImageAdapter extends RecyclerView.Adapter<ParameterVisualizationImageAdapter.ViewHolder> {
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;


        public ViewHolder(View parameterVisualizationImageView) {
            super(parameterVisualizationImageView);

            image = parameterVisualizationImageView.findViewById(R.id.parameter_visualization_image);
        }
    }

    private List<ParameterVisualizationModel> parameterVisualizationModels;

    public ParameterVisualizationImageAdapter(List<ParameterVisualizationModel> parameterVisualizationModels){
        this.parameterVisualizationModels = parameterVisualizationModels;
    }

    @Override
    public ParameterVisualizationImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View parameter_visualization_item_image = inflater.inflate(R.layout.parameter_visualization_item_images, parent, false);

        // Return a new holder instance
        final ParameterVisualizationImageAdapter.ViewHolder viewHolder = new ParameterVisualizationImageAdapter.ViewHolder(parameter_visualization_item_image);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ParameterVisualizationModel parameterVisualizationModel = parameterVisualizationModels.get(position);

        // Set item views based on your views and data model
        ImageView parameterVisualizationImage = viewHolder.image;

        //TODO This is just a placeholder image, it needs to use the image from the ParamterVirtualizationModels ImagePath
        parameterVisualizationImage.setImageResource(R.drawable.ic_exercise);
    }

    @Override
    public int getItemCount() {
        return parameterVisualizationModels.size();
    }
}
