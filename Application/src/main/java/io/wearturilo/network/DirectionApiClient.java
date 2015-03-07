package io.wearturilo.network;

import io.wearturilo.common.model.directions.Directions;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface DirectionApiClient {
    @GET("/maps/api/directions/json")
    Observable<Directions> directions(
            @Query("origin") String origin,
            @Query("destination") String destinationLat,
            @Query("mode") String mode);
}
