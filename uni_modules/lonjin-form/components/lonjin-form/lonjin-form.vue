<template>
	<view class="lonjin-form-wrapper">
		<slot name="title"></slot>
		<view class="items flex-bt" 
			:class="[getClassName(item)]" 
			v-for="(item,index) in config" :key="index"
		>
			<view class="label flex-start" :class="[item.verify ? 'active' : '']">
				<text>*</text>
				{{item.label}}
			</view>
			<view class="value flex-end">
				<template v-if="item.type=='input'">
					<input :type="item.inputType" :value="form[item.key]" @input="change" :data-key="item.key" :placeholder="item.placeholder">
					<view class="unit">
						{{item.unit || ''}}
					</view>
				</template>
				<template v-if="item.type=='picker'">
					<view class="picker flex-end">
						<picker @change="pickerChange" range-key="label" :data-options="item.options" :data-key="item.key" :value="findIndex(item.options,item.key)" :range="item.options">
							<view class="uni-input" :class="[getLabel(item.options, item.key) ? '' : 'placeholder']">{{ getLabel(item.options, item.key) ? getLabel(item.options, item.key) : item.placeholder}}</view>
						</picker>
						<image class="icon" src="../../static/images/icon-right.png"></image>
					</view>
				</template>
				<template v-if="item.type=='multipleChoice'">
					<view class="options flex-start">
						<view class="options-item flex-start" @click="chooseItem(item.key, child.value)" v-for="(child,childIndex) in item.options" :key="childIndex">
							<rrf-checkbox type="big" :checked="isChecked(item.key, child.value)"></rrf-checkbox>
							<text>{{child.label}}</text>
						</view>
					</view>
				</template>
				<template  v-if="item.type=='uploadMedia'">
					<view class="upload flex-end" @click="upload(item)">
						<template v-if="form[item.key]">
							已上传
							<image class="icon" src="../../static/images/add-icon-2.png"></image>
						</template>
						<template v-else>
							请上传
							<image class="icon" src="../../static/images/add-icon-2.png"></image>
						</template>
					</view>
				</template>
				<template v-if="item.type=='date' || item.type == 'time'">
					<view class="picker flex-end">
						<picker :start="item.start" :end="item.end" :fields="item.fields || 'day'" @change="dateChange" :mode="item.type" :data-key="item.key" :value="form[item.key]">
							<view class="uni-input" :class="[form[item.key] ? '' : 'placeholder']">{{form[item.key] ? form[item.key] : item.placeholder}}</view>
						</picker>
						<image class="icon" src="../../static/images/icon-right.png"></image>
					</view>
				</template>
				<template  v-if="item.type=='textarea'">
					<textarea :value="form[item.key]" @input="change" :data-key="item.key" :placeholder="item.placeholder"  cols="30" rows="10"></textarea>
				</template>
				<template v-if="item.type == 'pictureGroup'">
					<view class="pictureGroup flex-start">
						<view class="child flex-center" v-for="(child, childIndex) in form[item.key]" :key="childIndex">
							<image @click="previewImage(item, child)" :src="child"  mode="widthFix"></image>
							<image @click="deleteImage(item, child)" src="../../static/images/close.png" class="close"></image>
						</view>
						<view v-if="!form[item.key] || (form[item.key].length) < (item.max || 1)" class="add flex-center" @click="uploadPicture(item)">
							<image src="../../static/images/add-icon-2.png" mode=""></image>
							<view>请上传</view>
						</view>
					</view>
				</template>
			</view>
			<slot name="other"></slot>
		</view>
		<template v-if="showButton">
			<view style="height: 300rpx;"></view>
			<view class="fixed-bottom ios-bottom">
			    <view class="content-box flex-center">
			        <view class="btn" @click="save">
			            <rrf-button block="block" :type="buttonType" size="large">{{buttonText}}</rrf-button>
			        </view>
			    </view>
			</view>
		</template>
	</view>
</template>
<script>
</script>
<script>
	import RrfCheckbox from './components/RrfCheckbox.vue'
	import RrfButton from "./components/RrfButton.vue"
	export default {
		components: {RrfCheckbox, RrfButton},
		props: {
			config: {
				type: Array,
				default: ()=> []
			},
			formData: {
				type: Object,
				default: ()=> {}
			},
			buttonText: {
				type: String,
				default: ()=> '提交'
			},
			rules: {
				type: Object,
				default: ()=> {}
			},
			showButton: {
				type: Boolean,
				default: ()=> true
			}
		},
		
		data() {
			return {
				form: {},
				buttonType: 'disabled'
			}
		},
		
		watch: {
			formData: {
				handler(val) {
					this.form = JSON.parse(JSON.stringify(val))
					this.$nextTick(()=>{
						this.buttonType = this.changeButtonType()
					})
				},
				deep: true,
				immediate: true
			},
		},
		
		methods: {
			// 添加类名
			getClassName(item) {
				if(item.type=='multipleChoice' || item.type == 'pictureGroup') return 'active'
				if(item.type=='textarea') return 'active2'
			},
			// 表单验证
			changeButtonType() {
				// 遍历对象的所有属性
				for (let index in this.config) {
					if(this.config[index].verify) {
						const value = this.form[this.config[index].key]
						if (value === null || value === undefined || value === '' || Array.isArray(value) && value.length === 0) {
						   return 'disabled';
						}
					}
				}
				return 'info'; // 所有属性的值都不为空，返回 true
			},
			
			formValidation() {
				for (let index in this.config) {
					if(this.config[index].verify) {
						const value = this.form[this.config[index].key]
						const rules = this.rules[this.config[index].key]
						const result = this.mapRules(rules, value)
						return result
					}
				}
				return true; // 所有属性的值都不为空，返回 true
			},
			
			mapRules(rules, value) {
				if(rules){
					for(let i in rules){
						let {required, min, max, regular, message, fn} = rules[i]
						if(value === null || value === undefined || value === '' || Array.isArray(value) && value.length === 0){
							this.toast(message)
							return false
						}
						if((min !== undefined) && (max !== undefined)) {
							if(value.length < min || value.length > max){
								this.toast(message)
							}
						}
						if(regular !== undefined) {
							let test = new RegExp(regular.slice(1,-1)).test(value)
							if(!test) {
								this.toast(message)
								return false
							}
						}
					}
					return true
				}else{
					console.error('请配置rules')
				}
			},
			
			toast(title, icon = 'none', duration = 2000) {
				uni.showToast({title, icon, duration})
			},
			
			change(e) {
				this.form[e.currentTarget.dataset.key] = e.detail.value
				this.$emit('updateValue', this.form)
			},
			
			pickerChange(e) {
				this.form[e.currentTarget.dataset.key] = e.currentTarget.dataset.options[e.detail.value].value
				this.$emit('updateValue', this.form)
			},
			
			dateChange(e) {
				this.form[e.currentTarget.dataset.key] = e.detail.value
				this.$emit('updateValue', this.form)
			},
			
			findIndex(options, key) {
				let index = options.reduce((val, item, index)=>{
					if(item.value === this.form[key]){
						val = index
					}
					return val
				}, 0)
				return index
			},
			
			// 获取label名称
			getLabel(options, key) {
				const value = this.form[key]
				if(value === null || value === undefined || value === ''){
					return ''
				}else{
					let label = options.reduce((val, item)=>{
						value === item.value && (val = item.label)
						return val
					}, '')
					return label
				}
			},
			
			// 多选处理
			chooseItem(key, value) {
				!Array.isArray(this.form[key]) && (this.form[key] = [])
				
				let activeIndex = this.form[key].indexOf(value)
				if(activeIndex === -1){
					this.form[key] = [...this.form[key], value]
				}else{
					this.form[key] = this.form[key].reduce((arr,item,index)=>{
						value !== item && arr.push(item)
						return arr
					}, [])
				}
				this.$emit('updateValue', this.form)
			},
			
			isChecked(key, value) {
				!Array.isArray(this.form[key]) && (this.form[key] = [])
				return this.form[key].includes(value)
			},
			
			// 图片组上传
			uploadPicture(item) {
				const _this = this
				let {
					key, 
					mediaType = ['image'],
					sourceType = ['album', 'camera'], 
					maxDuration = 30, 
					camera = 'back'
				} = item
				uni.chooseMedia({
					count: 1,
					mediaType,
					sourceType,
					maxDuration,
					camera,
					success(res) {
						_this.$emit('uploadPicture',{tempFiles: res.tempFiles, key})
					},
					fail(error) {
						console.error(error,'--lonjin-form')
					}
				})
			},
			
			//图片预览
			previewImage(item, current) {
				let url = this.form[item.key]
				uni.previewImage({
				    urls: url,
				})
			},
			
			// 删除图片
			deleteImage(item, url){
				this.form[item.key] = this.form[item.key].reduce((arr,item)=>{
					item !== url && arr.push(item)
					return arr
				},[])
				this.$emit('updateValue', this.form)
			},
			
			
			// 图片/视频上传
			upload(item) {
				const _this = this
				let {
					key, 
					count = '9', 
					mediaType = ['image','video'],
					sourceType = ['album', 'camera'], 
					maxDuration = 30, 
					camera = 'back'
				} = item
				uni.chooseMedia({
					count,
					mediaType,
					sourceType,
					maxDuration,
					camera,
					success(res) {
						_this.$emit('uploadMedia',{tempFiles: res.tempFiles, key})
					},
					fail(error) {
						console.error(error,'--lonjin-form')
					}
				})
			},
			
			save() {
				if(this.buttonType == 'info' && this.formValidation()) {
					this.$emit('submit', this.form)
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	@import '../../static/base.scss';
	.lonjin-form-wrapper{
		width: 100%;
		.fixed-bottom {
		    position: fixed;
		    left: 0;
		    bottom: 0;
		    height: 204rpx;
		    width: 100%;
		    box-sizing: content-box;
		    background: #ffffff;
		    box-shadow: 0px -8rpx 20rpx 0px rgba(0, 0, 0, 0.06);
		    border-radius: 24rpx 24rpx 0rpx 0rpx;
		    z-index: 200;
		    .content-box {
		        width: 100%;
		        padding: 32rpx;
		        .btn {
		            width: 100%;
		        }
		    }
		}
		.items{
			width: 100%;
			height: 104rpx;
			padding: 0 32rpx;
			background: #FFFFFF;
			box-shadow: inset 0rpx -1rpx 0rpx 0rpx #EEEEEF;
			.pictureGroup{
				width: 100%;
				flex-wrap: wrap;
				.child{
					width: 180rpx;
					height: 180rpx;
					border-radius: 8rpx;
					background-color: rgba(230, 239, 255, 1);
					margin-bottom: 32rpx;
					margin-right: 32rpx;
					position: relative;
					.close{
						position: absolute;
						width: 40rpx;
						height: 40rpx;
						top: -18rpx;
						right: -18rpx;
						
					}
					image{
						width: 100%;
						height: 100%;
					}
				}
				.add{
					width: 180rpx;
					height: 180rpx;
					border-radius: 8rpx;
					background-color: rgba(230, 239, 255, 1);
					margin-bottom: 32rpx;
					flex-wrap: wrap;
					flex-direction: column;
					image{
						width: 40rpx;
						height: 40rpx;
					}
					view{
						width: 100%;
						text-align: center;
						color: #0D67FF;
						font-size: 28rpx;
						margin-top: 12rpx;
					}
				}
			}
			&.active2{
				height: auto;
				flex-wrap: wrap;
				.label{
					width: 100%;
					padding-top: 28rpx;
				}
				.value{
					width: 100%;
					flex: inherit;
					margin-top: 32rpx;
				}
			}
			&.active{
				height: auto;
				flex-wrap: wrap;
				.label{
					width: 100%;
					padding-top: 28rpx;
				}
				.value{
					width: 100%;
					margin-top: 44rpx;
					.options{
						flex: 1;
						flex-wrap: wrap;
						align-items: inherit;
						.options-item{
							height: 56rpx;
							margin-bottom: 32rpx;
							margin-right: 24rpx;
							text{
								margin-left: 8rpx;
							}
						}
					}
				}
			}
			.label{
				font-size: 32rpx;
				font-family: PingFangSC-Regular, PingFang SC;
				font-weight: 400;
				color: #474A50;
				line-height: 32rpx;
				text{
					opacity: 0;
					margin-right: 4rpx;
				}
				&.active{
					text{
						opacity: 1;
						color: #FF5B57;
					}
				}
			}
			.value{
				flex: 1;
				input{
					text-align: right;
					margin-right: 16rpx;
				}
				textarea{
					width: 100%;
					display: block;
					height: 120rpx;
				}
				.upload{
					font-size: 32rpx;
					font-family: PingFangSC-Regular, PingFang SC;
					font-weight: 400;
					color: #0D67FF;
					line-height: 32rpx;
					.icon{
						width: 40rpx;
						height: 40rpx;
						display: block;
						margin-left: 8rpx;
					}
				}
				.picker{
					.icon{
						width: 32rpx;
						height: 32rpx;
						display: block;
						margin-left: 16rpx;
					}
					.uni-input{
						&.placeholder{
							color: rgba(140, 143, 148, 1);
						}
					}
				}
			}
		}
	}
</style>
