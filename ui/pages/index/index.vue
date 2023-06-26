<template>
	<view>
		<uni-search-bar placeholder="请输入需要搜索的内容" @confirm="search" :focus="true" v-model="searchValue">
		</uni-search-bar>
		<view class="uni-margin-wrap">
			<swiper class="swiper" circular :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval"
				:duration="duration">
				<swiper-item v-for="item in swiperList">
					<image style="width: 100%;height: 100%" mode="aspectFill" :src="item.imageUrl"></image>
				</swiper-item>
			</swiper>
		</view>
		<uni-section title="维修项目" type="line">
			<uni-grid :column="2" :highlight="true" @change="change">
				<uni-grid-item v-for="(item, index) in tainList" :index="index" :key="index">
					<view class="grid-item-box" style="background-color: #fff;">
						<image style="width: 100px;height: 100px;" mode="aspectFill" :src="item.imageUrl"></image>
						<text class="text">{{item.name}}</text>
						<button type="primary" size="mini" @click="torder(item.id)">立即查看</button>
					</view>
				</uni-grid-item>
			</uni-grid>
		</uni-section>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				indicatorDots: true,
				autoplay: true,
				interval: 2000,
				duration: 500,
				searchValue: '',
				swiperList:[{
					imageUrl:'https://editor-img.ibaotu.com/ips_templ_preview_t/2020-09-23/70/7045c6a1-b8ef-4729-a71e-0e76b0331638.jpg?auth_key=1683792215-0-0-b9a17c7e29544107012129636ae09928&x-oss-process=image/resize,w_580,m_lfit,image/bright,10'
				},{
					imageUrl:'https://1105886.s81i.faiusr.com/2/101/AFEI3r9DEAIYACCyzbbwBSjg0IkIMO4FOKwCQGU.jpg'
				}],
				tainList:[{
					id:1,
					name:'洗衣机',
					imageUrl:'https://ts1.cn.mm.bing.net/th/id/R-C.9338668257b7f8731cfedea65b990dde?rik=GL2SrqDOjS8hzw&riu=http%3a%2f%2fp10.qhimg.com%2ft01731cd4175abb5eb5.jpg&ehk=wKM8sWuiXtVsnpv20%2fLNrSpzgSekIprnzkkaL4GyvnI%3d&risl=&pid=ImgRaw&r=0'
				}]
			}
		},
		onLoad() {

		},
		methods: {
			search() {

			},
			// 跳转到下单页
			torder(id) {
				uni.navigateTo({
					url:'/pages/index/order?orderId='+id
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.uni-margin-wrap {
		width: 690rpx;
		width: 100%;
	}

	.swiper {
		height: 350rpx;
	}

	.swiper-item {
		display: block;
		height: 300rpx;
		line-height: 300rpx;
		text-align: center;
	}

	.grid-item-box {
		flex: 1;
		/* position: relative; */
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding: 15px 0;
	}



	.uni-bg-red {
		background-color: red;
	}

	.uni-bg-green {
		background-color: green;
	}

	.uni-bg-blue {
		background-color: blue;
	}
</style>