package com.lovephotos.collageeditor.stpnfncmakads_tom.retrofit;

import com.lovephotos.collageeditor.stpnfncmakads_tom.model.Settings;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{fullUrl}")
    Call<Settings> getAdIds(@Path("fullUrl") String fullUrl);
}
