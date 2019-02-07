package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoadImageTask;

public class ParameterVisualizationImageAdapter extends RecyclerView.Adapter<ParameterVisualizationImageAdapter.ViewHolder> {
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;


        public ViewHolder(View parameterVisualizationImageView) {
            super(parameterVisualizationImageView);

            image = parameterVisualizationImageView.findViewById(R.id.parameter_visualization_image);
        }
    }

    private List<String> imagePaths;


    public ParameterVisualizationImageAdapter(List<String> imagePaths){
        this.imagePaths = imagePaths;
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String imagePath = imagePaths.get(position);

        // Set item views based on your views and data model
        ImageView parameterVisualizationImage = viewHolder.image;

        //TODO This downloads the same image for every url string in the list, fix.!!!
        LoadImageTask task = new LoadImageTask(parameterVisualizationImage);
        task.execute(imagePath);
    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }
}
