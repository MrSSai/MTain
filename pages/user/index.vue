<template>
  <view class="mypage-container">
    <!-- 自定义头部 -->
    <view class="head"></view>
    <!-- 个人信息模块 -->
    <view class="user-info-box">
      <view class="avatar-box">
        <image class="avatar" src="../../static/image/logo.png"></image>
        <view class="avatar-text">
          <view class="name">登录/注册</view>
          <!-- <view class="desc">天道有常，或因势而迟，然终不误。</view> -->
        </view>
      </view>
      <view class="card-three"></view>
      <view class="card-two"></view>
      <view class="card-one"></view>
    </view>
    <!-- 占位 空出user-info-box的距离 -->
    <view class="size-seat">
    </view>
    <!-- 中间部分 -->
    <div class="user-center-box">
      <view class="user-center-item">
        <view class="user-center-star-icon">
          <image src="../../static/image/yuyue.png"></image>
        </view>
        <view class="user-center-star-title">
          我的预约
        </view>
      </view>
      <view class="user-center-item">
        <view class="user-center-star-icon">
          <image src="../../static/image/dealing.png"></image>
        </view>
        <view class="user-center-star-title">
          处理中
        </view>
      </view>
      <view class="user-center-item">
        <view class="user-center-star-icon">
          <image src="../../static/image/all-order.png"></image>
        </view>
        <view class="user-center-star-title">
          历史订单
        </view>
      </view>
    </div>

    <!-- 关于我们 -->
    <view class="menu-box">
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/contact.png"></image>
          <view class="menu-item-left-text">
            关于我们
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
    </view>
    
    <!-- 我的积分/我的任务/我的收藏 -->
   <!-- <view class="menu-box">
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            我的积分
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            我的任务
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            我的收藏
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
    </view> -->
    <!-- 我的下载/我的粉丝/联系我们 -->
    <!-- <view class="menu-box">
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            我的下载
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            我的粉丝
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
      <view class="menu-item">
        <view class="menu-item-left">
          <image src="../../static/image/logo.png"></image>
          <view class="menu-item-left-text">
            联系我们
          </view>
        </view>
        <view class="menu-item-right">
          <view class="arrow-right"></view>
        </view>
      </view>
    </view> -->
  </view>
</template>

<script>
  export default {
    data() {
      return {
        title: 'Hello'
      }
    },
    onLoad() {

    },
    methods: {

    }
  }
</script>

<style>
  /* 自定义头部 */
  .head {
    height: 140rpx;
  }

  /* 用户信息部分 */
  .user-info-box {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 300rpx;
    background: linear-gradient(90deg, #00B26A 0, #2DB259 45%, #4AB262);
  }

  .avatar-box {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    align-items: center;
    padding-left: 40rpx;
  }

  .avatar-box .avatar {
    width: 116rpx;
    height: 116rpx;
    border-radius: 116rpx;
  }

  .avatar-box .avatar-text {
    padding-left: 20rpx;
  }

  .avatar-text .name {
    font-size: 20px;
    font-weight: 500;
    color: #fff;
  }

  .avatar-text .desc {
    color: #fff;
    padding-top: 10rpx;
    font-size: 12px;
  }

  .card-one {
    height: 40rpx;
    width: 100%;
    background: #ffffff;
    border-radius: 30px 30px 0px 0px;
    position: absolute;
    bottom: 0px;
  }

  .card-two {
    height: 40rpx;
    width: 92%;
    background: #ffffffb0;
    border-radius: 10px 10px 0px 0px;
    position: absolute;
    bottom: 5px;
    left: 4%;
    right: 6%;
  }

  .card-three {
    position: absolute;
    bottom: 10px;
    left: 6%;
    right: 8%;
    height: 40rpx;
    width: 88%;
    background: #ffffff59;
    border-radius: 10px 10px 0px 0px;
  }

  .size-seat {
    margin-top: 125rpx;
  }

  /* 中间部分 */
  .user-center-box {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    padding: 0 30rpx;
  }

  .user-center-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    background-color: #fff;
    width: 180rpx;
    margin: 50rpx 10rpx 0 10rpx;
    padding: 40rpx 30rpx 40rpx 30rpx;
    box-shadow: 0 0px 20px rgba(231, 231, 231, 0.6);
    border-radius: 16rpx;
  }

  .user-center-star-icon {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 80rpx;
    height: 80rpx;
    border-radius: 80rpx;
    border-radius: 80rpx;
    background-image: linear-gradient(to bottom right, #4CCE84, #4BCD85, #49CD84);
    margin-right: 10rpx;
  }

  .user-center-star-icon image {
    width: 60%;
    height: 60%;
  }

  .user-center-star-title {
    font-weight: 550;
    font-size: 12px;
    padding-top: 10rpx;
    color: #303133;
  }

  /* 菜单区域 */
  .menu-box {
    box-shadow: 0 0px 20px rgba(231, 231, 231, 0.6);
    border-radius: 16rpx;
    margin: 40rpx 40rpx 0 40rpx;
  }

  .menu-item {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1rpx solid #F6F6F6;
    padding: 30rpx;
  }

  .menu-item-left {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
  }

  .menu-item-left image {
    width: 38rpx;
    height: 38rpx;
  }

  .menu-item-left-text {
    padding-left: 20rpx;
    font-size: 14px;
  }

  .arrow-right {
    width: 15rpx;
    height: 15rpx;
    border-top: 3rpx solid #a9acb3;
    border-right: 3rpx solid #a9acb3;
    transform: rotate(45deg);
  }
</style>
