package io.agora.livedemo.ui.cdn.presenter;


import io.agora.live.cdn.presenter.CdnHostPresenter;
import io.agora.livedemo.common.utils.ThreadManager;
import io.agora.livedemo.data.model.AgoraTokenBean;
import io.agora.livedemo.data.model.CdnUrlBean;
import io.agora.livedemo.data.restapi.LiveException;
import io.agora.livedemo.data.restapi.LiveManager;
import retrofit2.Response;

public class CdnLiveHostPresenterImpl extends CdnHostPresenter {
    @Override
    public void onStartCamera() {
        runOnUI(() -> {
            if (isActive()) {
                mView.onStartBroadcast();
            }
        });
    }

    @Override
    public void switchCamera() {
        runOnUI(() -> {
            if (isActive()) {
                mView.switchCamera();
            }
        });
    }

    @Override
    public void leaveChannel() {
        runOnUI(() -> {
            if (isActive()) {
                mView.onLeaveChannel();
            }
        });
    }

    @Override
    public void getFastToken(String hxId, String channel, String hxAppkey, int uid, boolean isRenew) {
        ThreadManager.getInstance().runOnIOThread(() -> {
            try {
                Response<AgoraTokenBean> response = LiveManager.getInstance().getAgoraToken(hxId, channel, hxAppkey, uid);
                runOnUI(() -> {
                    if (isActive()) {
                        mView.onGetTokenSuccess(response.body().getAccessToken(), response.body().getAgoraUserId(), isRenew);
                    }
                });
            } catch (LiveException e) {
                e.printStackTrace();
                runOnUI(() -> {
                    if (isActive()) {
                        mView.onGetTokenFail(e.getDescription());
                    }
                });
            }
        });
    }

    @Override
    public void getCdnUrl(String channel) {
        ThreadManager.getInstance().runOnIOThread(() -> {
            try {
                Response<CdnUrlBean> response = LiveManager.getInstance().getCdnPushUrl(channel);
                runOnUI(() -> {
                    if (isActive()) {
                        mView.onGetCdnUrlSuccess(response.body().getData());
                    }
                });
            } catch (LiveException e) {
                e.printStackTrace();
                runOnUI(() -> {
                    if (isActive()) {
                        mView.onGetCdnUrlFail(e.getDescription());
                    }
                });
            }
        });
    }

    @Override
    public void deleteRoom(String roomId) {
        ThreadManager.getInstance().runOnIOThread(() -> {
            LiveManager.getInstance().deleteRoom(roomId);
        });
    }
}

