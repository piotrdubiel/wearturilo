package io.wearturilo.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import io.wearturilo.R;
import io.wearturilo.common.model.directions.Directions;
import io.wearturilo.common.model.directions.Step;
import java.util.LinkedList;
import java.util.List;

public class TraceListAdapter extends RecyclerView.Adapter<TraceListAdapter.ViewHolder> {

    private final List<Step> stepList;


    public TraceListAdapter() {
        stepList = new LinkedList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_text_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TraceListAdapter.ViewHolder holder, int position) {
        holder.textView.setText(Html.fromHtml(stepList.get(position).instructions));
        if (stepList.get(position).maneuver != null) {
            holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(stepList.get(position).maneuver.getDirection(), 0, 0, 0);
        }
        else {
            holder.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_straight_36dp, 0, 0, 0);
        }
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public void fillListByNewItem(Directions direction) {
        stepList.clear();
        List<Step> steps = direction.getSteps();
        for (Step step : steps) {
            stepList.add(step);
        }
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.label)
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
