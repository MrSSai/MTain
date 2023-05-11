<template>
	<view class="button-wrapper" :class="[type,block,size,plain?'plain': '',disabled ? 'disabled' : '',gray ? 'gray' : '',danger ? 'danger' : '']">
		<slot></slot>
	</view>
</template>

<script>
	export default {
		props: {
			type: {
				type: String,
				default: function() {
					return 'primary'
				}
			},
			block: {
				type: String,
				default: function() {
					return ''
				}
			},
			size: {
				type: String,
				default: function() {
					return 'normal'
				}
			},
			//朴素按钮 白色背景
			plain: {
				type: Boolean,
				default: function() {
					return false
				}
			},
			disabled: {
				type: Boolean,
				default: function() {
					return false
				}
			},
			gray: {
				type: Boolean,
				default: function() {
					return false
				}
			},
			danger: {
				type: Boolean,
				default: function() {
					return false
				}
			}
		},
		data() {
			return {
				
			}
		},
	}
</script>

<style lang="scss" scoped>
	.button-wrapper{
		border-radius: 12rpx;
		padding: 20rpx 32rpx;
		font-size: 36rpx;
		line-height: 36rpx;
		font-family: PingFangSC-Medium, PingFang SC;
		font-weight: 500;
		display: inline-block;
		text-align: center;
		box-sizing: border-box;
		letter-spacing: 0.5px;
		overflow: hidden;
		&.primary{
			background: #FFFFFF;
			border: 2px solid #DDDDDF;
			color: #8C8F94;
		}
		&.info{
			background: #0D67FF;
			border: 1px solid #0D67FF;
			color: #FFFFFF;
			&.plain{
				background: #FFFFFF;
				color: #0D67FF;
			}
		}
		&.success{
			background: #00C96E;
			border: 1px solid #00C96E;
			color: #FFFFFF;
			&.plain{
				background: #FFFFFF;
				color: #00C96E;
			}
		}
		&.danger{
			background: #FF4C3F;
			color: #FFFFFF;
			border: 1px solid #FF4C3F;
			&.plain{
				background: #FFFFFF;
				color: #FF4C3F;
			}
		}
		&.warning{
			background: #FFB10A;
			color: #FFFFFF;
			border: 1px solid #FFB10A;
			&.plain{
				background: #FFFFFF;
				color: #FFB10A;
			}
		}
		&.block{
			display: block;
			width: 100%;
			padding-left: 0;
			padding-right: 0;
		}
		&.disabled{
			background: #E6EFFF;
			color: rgba(26, 26, 26, 0.3);
			border-color: #E6EFFF;
		}
		&.gray{
			background: #E6EFFF;
			color: #C0C3C7;
			border-color: #E6EFFF;
		}
		// large、normal、small、mini
		&.large{
			padding: 32rpx 52rpx;
			border-radius: 16rpx;
		}
		&.normal{
			padding: 20rpx 32rpx;
		}
		&.small{
			padding: 12rpx 24rpx;
			font-size: 28rpx;
			line-height: 40rpx;
		}
		&.mini{
			padding: 10rpx 16rpx;
		}
	}
</style>
