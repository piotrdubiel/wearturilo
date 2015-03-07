package io.wearturilo.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.wearturilo.common.model.Station;
import io.wearturilo.common.model.StationList;
import java.util.LinkedList;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Station> stationList;

    private OnStationItemClickListener onStationItemClickListener = OnStationItemClickListener.NULL;

    public StationListAdapter() {
        stationList = new LinkedList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout., false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStationItemClickListener.onStationItemClick(stationList.get(viewHolder.getPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void fillListByNewItem(StationList stationListObject) {
        stationList.clear();
        for (Station station : stationListObject) {
            stationList.add(station);
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnStationItemClickListener{
        OnStationItemClickListener NULL = new OnStationItemClickListener() {
            @Override
            public void onStationItemClick(Station station) {

            }
        };

        void onStationItemClick(Station station);
    }

}
