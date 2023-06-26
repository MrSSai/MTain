# lonjin-form动态表单

> lonjin-form：基于uni-app原生组件，无任何第三方依赖；配置简单、无任何第三方依赖，支持`输入框`、`多行文本`、`单选`、`多选`、`图片/视频上传`、`多图片上传`；支持表单验证、默认数据等。

## 使用方法

### demo

```js
<template>
	<view class="content">
		<view class="title">基础表单</view>
		<lonjin-form @submit="submit" @uploadMedia="uploadMedia" :formData="formData" @updateValue="updateValue" :config="config">
		</lonjin-form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				formData: {},
				config: [
					{label: '输入框',placeholder: '请输入', key: 'temperature', type: 'input',unit: '℃', inputType: 'text'},
					{label: '单选', placeholder: '请选择', key: 'age', type: 'picker',options: [{label: '1岁',value: '1'},{label: '2岁',value: '2'}],},
					{label: '多选', key: 'bloodPressure', options: [{label: '看电影', value: '1'},{label: '下棋',value: '2'}],type: 'multipleChoice'},
					{label: '文本框', placeholder: '请输入', key: 'remark',type: 'textarea'},
					{label: '图片/视频', placeholder: '请上传图片', key: 'images',type: 'uploadMedia'},
					{label: '日期', placeholder: '请选择日期', key: 'date',type: 'date'},
					{label: '时间',placeholder: '请选择时间', key: 'time',type: 'time'},
					{label: '多图上传', placeholder: '请上传图片', key: 'picture',type: 'pictureGroup', max: 2}
				]
			}
		},
		methods: {
			// 更新数据
			updateValue(val) {
				this.formData = val
			},

			// 提交数据
			submit(val) {
				console.log(val)
				console.log(this.formData)
			},
			
			// 图片/视频上传
			uploadMedia(e) {
				// 如果 在formData中没有定义该字段，则需要进行深拷贝
				const data = JSON.parse(JSON.stringify(this.formData))
				data[e.key] = e.tempFiles
				this.formData = JSON.parse(JSON.stringify(data))
				
				/* 如果定义了formData: {images: ''}，则不需要进行深拷贝 
				this.formData[e.key] = e.tempFiles
				*/
			},
			
			// 多图上传
			uploadPicture(e) {
				// 如果 在formData中没有定义该字段，则需要进行深拷贝
				const data = JSON.parse(JSON.stringify(this.formData))
				if(data[e.key] && data[e.key].length){
					data[e.key] = [...data[e.key], e.tempFiles[0].tempFilePath]
				}else{
					data[e.key] = [e.tempFiles[0].tempFilePath]
				}
				this.formData = JSON.parse(JSON.stringify(data))
				/* 如果定义了formData: {images: ''}，则不需要进行深拷贝 
				this.formData[e.key] = [...this.formData[e.key], ...e.tempFiles[0].tempFilePath]
				*/
			},
		}
	}
</script>
```

## 配置说明 

| 参数/事件       | 说明                         | 默认值 | 类型  |
| ----------- | ---------------------------- | ------ | ----- |
| formData | 表单数据 | {}     | Object |
| config   | 表单配置项，具体见下方文档 | []     | Array<Object> |
| showButton   | 是否显示提交按钮 | true     | Boolean |
| buttonText   | 提交按钮文字 | 提交     | String |
| rules   | 表单验证规则 | []     | Object |
| @updateValue   | 用于更新formData中的数据 | --     | Function |
| @submit   | showButton为true时，点击提交按钮触发 | --     | Function |
| @uploadMedia   | 图片/视频上传回调函数 | --     | Function |
| @uploadPicture   | 图片视频上传回调函数（图片组）| --     | Function |


### config 配置说明

| 字段名       | 说明                         | 可选值 | 默认值  |
| ----------- | ---------------------------- | ------ | ----- |
| label |  标签文本     | -- | -- |
| placeholder |  默认展示文字     | -- | -- |
| key |  表单域 model 字段名称     | -- | -- |
| type |  表单类型     | input/textarea/picker/bloodPressure/uploadMedia/time/date/pictureGroup | -- |
| unit |  数据单位，在type等于input时生效     | -- | -- |
| inputType |  input类型，在type等于input时生效     | 见uni-app中input的type | -- |
| verify |  是否必填项，如未传rules，则只验证数据是否有值     | true/false | -- |
| options |  type为picker/bloodPressure时有效，结构为[{label: '', value:''}]     | [] | -- |
| count |  type为uploadMedia时生效，具体见[uni.chooseMedia](https://uniapp.dcloud.net.cn/api/media/video.html#choosemedia)    | 1-9 | 9 |
| mediaType | type为uploadMedia时生效，具体见[uni.chooseMedia](https://uniapp.dcloud.net.cn/api/media/video.html#choosemedia)   | ['image', 'video']	|['image', 'video']	|
| sourceType | type为uploadMedia时生效，具体见[uni.chooseMedia](https://uniapp.dcloud.net.cn/api/media/video.html#choosemedia)  | ['album', 'camera']	|['album', 'camera']|
| maxDuration | type为uploadMedia时生效，具体见[uni.chooseMedia](https://uniapp.dcloud.net.cn/api/media/video.html#choosemedia)  | 1-30	|30|
| camera | type为uploadMedia时生效，具体见[uni.chooseMedia](https://uniapp.dcloud.net.cn/api/media/video.html#choosemedia)  | back	|back|
| start |  type为date/time时生效，具体见[picker](https://uniapp.dcloud.net.cn/component/picker.html)     | -- | -- |
| end |  type为date/time时生效，具体见[picker](https://uniapp.dcloud.net.cn/component/picker.html)     | -- | -- |
| fields |  type为date时生效，具体见[picker](https://uniapp.dcloud.net.cn/component/picker.html)     | year/month/day | day |
| max |  type为pictureGroup时生效，最多上传几张图片   | 1-10 | 1 |



### rules 配置说明

目前`rules`支持验证是否为必填、是否符合输入长度、以及正则验证，如验证失败，则通过`uni`提供的`uni.showToast`方法弹出`message`；具体如下：

#### demo:
```html
<template>
	<view class="content">
		<view class="title">表单验证</view>
		<lonjin-form :rules="rules" @submit="submit" :formData="formData" @updateValue="updateValue" :config="config">
		</lonjin-form>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				formData: {},
				rules: {
					temperature: [
						{
							required: true,
							message: '请输入邮箱'
						},
						{
							min: 3,
							max: 11,
							message: '长度在 3 到 5 个字符'
						},
						{
							regular: '/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/',
							message: '不符合规则'
						}
					],
				},
				config: [
					{
						label: '输入框',
						placeholder: '请输入',
						key: 'temperature',
						type: 'input',
						unit: '℃',
						inputType: 'digit',
						verify: true
					},
				]
			}
		},
		methods: {
			updateValue(val) {
				this.formData = val
			},

			// 提交数据
			submit(val) {
				console.log(val)
				console.log(this.formData)
			},
		}
	}
</script>
```

#### 配置说明:

| 字段名       | 说明                         | 可选值 | 默认值  |
| ----------- | ---------------------------- | ------ | ----- |
| message     |  提示语                        | --| --  |
| required    |  必填项验证                     | true | true  |
| min/max    |  验证数据是否在指定区间            | -- | --  |
| regular    |  正则            | '' | ''  |


## 反馈&&交流

如有问题，可以评论区反馈，或者直接[联系作者](https://lonjinup.github.io/post/about/)