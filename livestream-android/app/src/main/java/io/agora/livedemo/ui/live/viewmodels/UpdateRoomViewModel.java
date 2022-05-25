package io.agora.livedemo.ui.live.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;

import io.agora.livedemo.common.reponsitories.AppServerRepository;
import io.agora.livedemo.common.reponsitories.Resource;
import io.agora.livedemo.data.model.LiveRoom;
import okhttp3.RequestBody;

public class UpdateRoomViewModel extends AndroidViewModel {
    private AppServerRepository repository;
    private MediatorLiveData<Resource<LiveRoom>> updateObservable;

    public UpdateRoomViewModel(@NonNull Application application) {
        super(application);
        repository = new AppServerRepository();
        updateObservable = new MediatorLiveData<>();
    }

    public MediatorLiveData<Resource<LiveRoom>> getUpdateObservable() {
        return updateObservable;
    }

    public void updateLiveRoom(String roomId, RequestBody body) {
        updateObservable.addSource(repository.updateLiveRoom(roomId, body), response -> updateObservable.postValue(response));
    }
}
