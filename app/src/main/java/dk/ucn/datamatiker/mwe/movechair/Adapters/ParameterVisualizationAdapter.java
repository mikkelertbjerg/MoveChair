package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoadImageTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoadPVMImageTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ParameterVisualizationViewModel;

public class ParameterVisualizationAdapter extends RecyclerView.Adapter<ParameterVisualizationAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rvImages;
        public TextView unit;
        public TextView value;
        public TextView description;


        public ViewHolder(View parameterVisualizationView) {
            super(parameterVisualizationView);

            rvImages = parameterVisualizationView.findViewById(R.id.rv_paramenter_visualization_images);
            unit = parameterVisualizationView.findViewById(R.id.parameter_visulization_unit);
            value = parameterVisualizationView.findViewById(R.id.parameter_visulization_value);
            description = parameterVisualizationView.findViewById(R.id.parameter_visulization_description);
        }
    }

    private List<ParameterVisualizationModel> parameterVisualizationModels;
    private Context context;
    private Bitmap pvmImage;

    public  ParameterVisualizationAdapter (List<ParameterVisualizationModel> parameterVisualizationModels, Context context, Bitmap pvmImage){
        this.parameterVisualizationModels = parameterVisualizationModels;
        this.context = context;
        this.pvmImage = pvmImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();


        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View parameter_visualization_item = inflater.inflate(R.layout.parameter_visulization_item, parent, false);

        // Return a new holder instance
        final ParameterVisualizationAdapter.ViewHolder viewHolder = new ParameterVisualizationAdapter.ViewHolder(parameter_visualization_item);


        //Create onClick
        //TODO OnClick goes to detailed view of achievement


        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ParameterVisualizationModel parameterVisualizationModel = parameterVisualizationModels.get(position);

        // Set item views based on your views and data model
        RecyclerView parameterVisualizationImage = viewHolder.rvImages;
        TextView parameterVisualizationUnit = viewHolder.unit;
        TextView parameterVisualizationValue = viewHolder.value;
        TextView parameterVisualizationDescription = viewHolder.description;

        parameterVisualizationUnit.setText(parameterVisualizationModel.getUnit());
        parameterVisualizationValue.setText(String.valueOf(parameterVisualizationModel.getValue()));
        parameterVisualizationDescription.setText(parameterVisualizationModel.getDescription());

        //Testing shyte
        //TODO This seems like a redundant list of images, find a way to let the ParameterVirtualizationImageAdapter recieve a list of simpler models or just a single url somehow...



        List<String> imagePaths = new ArrayList<>();
        for(ParameterVisualizationModel p : parameterVisualizationModels) {
            double numberOfImages = Math.floor(Double.valueOf(p.getValue() / p.getThreshold()));
            for(int i = 0; i < numberOfImages; i++) {

                String imagePath = p.getMedia().getPath();
                imagePaths.add(imagePath);
            }
        }

        ParameterVisualizationImageAdapter parameterVisualizationImageAdapter = new ParameterVisualizationImageAdapter(imagePaths, this.pvmImage);
        viewHolder.rvImages.setAdapter(parameterVisualizationImageAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(true);
        parameterVisualizationImage.setLayoutManager(linearLayoutManager);
    }

    @Override
    public int getItemCount() {
        return parameterVisualizationModels.size();
    }

}

