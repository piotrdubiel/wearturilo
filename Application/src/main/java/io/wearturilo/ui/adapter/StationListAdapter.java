package io.wearturilo.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import io.wearturilo.R;
import io.wearturilo.common.model.Station;
import io.wearturilo.common.model.StationList;
import io.wearturilo.common.utils.DistanceUtils;
import io.wearturilo.provider.UserDataProvider;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.ViewHolder> {

    private final List<Station> stationList;

    private OnStationItemClickListener onStationItemClickListener = OnStationItemClickListener.NULL;

    public StationListAdapter() {
        stationList = new LinkedList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_station, parent, false);
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
    public void onBindViewHolder(StationListAdapter.ViewHolder holder, int position) {
        holder.bind(stationList.get(position));
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void fillListByNewItem(StationList stationListObject,DistanceUtils distanceUtils, UserDataProvider userDataProvider) {
        stationList.clear();
        for (Station station : stationListObject) {
            final double distanceFromUser = distanceUtils.countDistance(
                                    station.getLatPos(),
                                    station.getLngPos(),
                                    userDataProvider.getLat(),
                                    userDataProvider.getLng());
            station.setDistanceFromUser(distanceFromUser);
            stationList.add(station);
        }
        Collections.sort(stationList);
        notifyDataSetChanged();
    }

    public void setOnStationItemClickListener(OnStationItemClickListener onStationItemClickListener) {
        this.onStationItemClickListener = onStationItemClickListener != null ? onStationItemClickListener : OnStationItemClickListener.NULL;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.id_tv_station_id)
        TextView tvStationId;

        @InjectView(R.id.id_tv_bike_number)
        TextView tvBikeNumber;

        @InjectView(R.id.id_tv_station_name)
        TextView tvStationName;

        @InjectView(R.id.id_tv_station_distance)
        TextView tvStationDistance;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        void bind(Station station) {
            tvStationId.setText(station.getStationNumber());
            tvBikeNumber.setText(String.valueOf(station.getBikeNumber().getNumber()));
            tvStationName.setText(station.getStationName());
            tvStationDistance.setText(String.format("%.2f km", station.getDistanceFromUser()));

        }
    }

    public interface OnStationItemClickListener {

        OnStationItemClickListener NULL = new OnStationItemClickListener() {
            @Override
            public void onStationItemClick(Station station) {

            }
        };

        void onStationItemClick(Station station);
    }

}
