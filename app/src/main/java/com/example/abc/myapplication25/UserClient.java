package com.example.abc.myapplication25;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserClient {

    @GET("imgs/?col=美女&tag=比基尼&sort=0&pn=10&rn=10&p=channel&from=1")
    Observable<BaiduImage> getImage();
}
