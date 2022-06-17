//
//  ELDChatView.h
//  EaseMobLiveDemo
//
//  Created by liu001 on 2022/5/14.
//  Copyright © 2022 zmw. All rights reserved.
//

#import <UIKit/UIKit.h>
@class JPGiftCellModel;

NS_ASSUME_NONNULL_BEGIN

@protocol ELDChatViewDelegate <NSObject>
@optional

- (void)chatViewDidBottomOffset:(CGFloat)offSet;

- (void)didSelectChangeCameraButton;

- (void)didSelectGiftButton;

- (void)didSelectedExitButton;

- (void)didSelectUserWithMessage:(AgoraChatMessage *)message;

- (void)chatViewDidSendMessage:(AgoraChatMessage *)message
                         error:(AgoraChatError *)error;

@end



@interface ELDChatView : UIView

@property (nonatomic,strong) EaseChatView *easeChatView;

@property (nonatomic,strong) AgoraChatroom *chatroom;

@property (nonatomic, weak) id<ELDChatViewDelegate> delegate;


- (instancetype)initWithFrame:(CGRect)frame
                     chatroom:(AgoraChatroom *)chatroom
                    isPublish:(BOOL)isPublish
              customMsgHelper:(EaseCustomMessageHelper *)customMsgHelper;


- (void)insertJoinMessageWithChatroom:(AgoraChatroom *)aChatroom user:(NSString *)aUsername;


- (void)showGiftModel:(ELDGiftModel*)aGiftModel
              giftNum:(NSInteger)giftNum
             backView:(UIView*)backView;

//有观众送礼物
- (void)userSendGiftId:(NSString *)giftId
               giftNum:(NSInteger)giftNum
                userId:(NSString *)userId
              backView:(UIView*)backView;


/*
 @param msg             接收的消息
 @param backView        展示在哪个页面
 */
//弹幕动画
- (void)barrageAction:(AgoraChatMessage*)msg backView:(UIView*)backView;

/*
 @param backView        展示在哪个页面
 */
//点赞动画
- (void)praiseAction:(UIView*)backView;

//reload tableView
- (void)reloadTableView;

@end

NS_ASSUME_NONNULL_END
