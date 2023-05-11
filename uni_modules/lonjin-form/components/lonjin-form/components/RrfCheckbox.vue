<template>
    <view :class="[type == 'small' ? 'checkbox-wrapper' : 'checkbox-wrapper big']" @click="choose">
        <template v-if="type == 'small'">
            <image v-show="!isChecked" src="../../../static/images/choose.png" mode=""></image>
            <image v-show="isChecked" src="../../../static/images/choose-active.png" mode=""></image>
        </template>
        <template v-if="type == 'checkbox'">
            <view class="check-box" :class="[isChecked ? 'active' : '']"></view>
        </template>
        <template v-else>
            <template v-if="disabled">
                <image src="../../../static/images/choose-big-disabled.png" mode=""></image>
            </template>
            <template v-else>
                <image v-show="!isChecked" src="../../../static/images/choose-big.png" mode=""></image>
                <image v-show="isChecked" src="../../../static/images/choose-big-active.png" mode=""></image>
            </template>
        </template>
    </view>
</template>

<script>
/* 
		单选框 
		checked 是否选中
		type 控制样式  参数 small big
		@changeChecked 修改checked
	*/
export default {
    props: {
        checked: {
            type: Boolean,
            default: function () {
                return false
            }
        },
        disabled: {
            type: Boolean,
            default: function () {
                return false
            }
        },
        type: {
            type: String,
            default: function () {
                return 'small'
            }
        },
        data: {
            default: function () {
                return ''
            }
        }
    },
    methods: {
        choose() {
            this.isChecked = !this.isChecked
        }
    },
    computed: {
        isChecked: {
            get() {
                return this.checked
            },
            set(val) {
                this.$emit('changeChecked', val, this.data)
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../../../static/base.scss';
.checkbox-wrapper {
    width: 32rpx;
    height: 32rpx;
    border-radius: 50%;
    overflow: hidden;
    &.big {
        width: 40rpx;
        height: 40rpx;
    }
    &.default {
        width: 37rpx;
        height: 37rpx;
    }
    image {
        width: 100%;
        height: 100%;
    }
    .check-box {
        width: 40rpx;
        height: 40rpx;
        border: 4rpx solid #C0C3C7;
        border-radius: 50%;
        // transition: all .4s;
        &.active {
            background: #e6efff;
            border: 10rpx solid #0D67FF;
        }
    }
}
</style>
