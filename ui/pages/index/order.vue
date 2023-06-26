<template>
	<view>
		<image mode="aspectFill"
			src="https://1105886.s81i.faiusr.com/2/101/AFEI3r9DEAIYACCyzbbwBSjg0IkIMO4FOKwCQGU.jpg"></image>
		<uni-section title="维修项目" type="line"><lonjin-form @submit="submit" :rules="rules" @uploadMedia="uploadMedia"
				:formData="formData" @updateValue="updateValue" :config="config">
			</lonjin-form>
		</uni-section>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				orderId: undefined,
				formData: {},
				config: [{
						label: '联系人',
						placeholder: '请输入',
						key: 'name',
						type: 'input',
						unit: '',
						inputType: 'text',
						verify: true
					},
					{
						label: '联系电话',
						placeholder: '请输入',
						key: 'phone',
						type: 'input',
						unit: '',
						inputType: 'text',
						verify: true
					},
					
					
					{
						label: '预约日期',
						placeholder: '请选择日期',
						key: 'date',
						type: 'date',verify:true
					},
					{
						label: '预约时间',
						placeholder: '请选择时间',
						key: 'time',
						type: 'time',verify:true
					},
					{
						label: '问题详情',
						placeholder: '请详细描述问题',
						key: 'remark',
						type: 'textarea',
						verify: true
					},
					{
						label: '多图上传',
						placeholder: '请上传图片',
						key: 'picture',
						type: 'pictureGroup',
						max: 2
					}
				],
				rules: {
					name: [{
							required: true,
							message: '请输入联系人名称'
						},
						{
							min: 3,
							max: 11,
							message: '长度在 3 到 5 个字符'
						}
					],
				},
			};
		},
		onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
			this.orderId = option.orderId
		},
		methods:{
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

<style lang="scss">

</style>