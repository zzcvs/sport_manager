<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<div_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></div_label>
					</el-col>
<!--					<el-col :span="4">-->
<!--						<div_label bg_color="bg_green" icon="el-icon-view" :url="url_article_hits" unit="次"-->
<!--								  title="文章浏览量"></div_label>-->
<!--					</el-col>-->
				</el-row>

				<el-row>
						<el-col v-if="$check_figure('/member_user/table')" :span="8">
						<div class="card chart">
									<newBarChart v-if="bar_obj_member_user.values.length > 0" id="bar_obj_member_user" :vm="bar_obj_member_user" :title="'会员用户统计'">
							</newBarChart>
							<div v-if="!bar_obj_member_user.values.length">会员用户没有符合条件的数据</div>
								</div>
					</el-col>
												<el-col v-if="$check_figure('/financial_statements/table')" :span="8">
						<div class="card chart">
									<newLineChart v-if="line_obj_financial_statements.values.length > 0" id="line_obj_financial_statements" :vm="line_obj_financial_statements" :title="'财务报表统计'">
							</newLineChart>
							<div v-if="!line_obj_financial_statements.values.length">财务报表没有符合条件的数据</div>
								</div>
					</el-col>
					</el-row>

			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import newBarChart from "@/components/charts/new_bar_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import div_label from "@/components/div_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			newBarChart,
			newLineChart,
			div_label
		},
		data() {
			return {
				activeName: "third",
					bar_obj_member_user: {
					names:[],
					xAxis: [],
					values:[]
				},
																line_obj_financial_statements:{
					names:[],
					xAxis: [],
					values:[]
				},
					url_user_count: "~/api/user/count?",
				url_article_hits: "~/api/article/sum?field=hits",
			};
		},
		created() {
				// 执行会员用户数据获取
			this.get_list_member_user();
										// 执行财务报表数据获取
			this.get_list_financial_statements();
			},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
				// 获取会员用户统计图
			async get_list_member_user() {
				let name_list = [];
				let query_str = "";
									let group_by_value = "member_name";
								let flag = false;
												let date_flag = "其他"
																																														name_list.push("账户余额");
				query_str = query_str+"account_balance"+","
									this.bar_obj_member_user.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
						await this.$get(
						"~/api/member_user/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_member_user.xAxis = xAxis;
								this.bar_obj_member_user.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_member_user.xAxis,true);
							}
						});
			},
															// 获取财务报表统计图数据
			async get_list_financial_statements() {
									let group_by_value = "report_name";
				let data = {};
								let flag = false;
												await this.$get(
					"~/api/financial_statements/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].report_name);
							}
							this.line_obj_financial_statements.names = name_list;
							this.get_list_financial_statements_sub("report_name",flag);
						}
				});
																																	},
			async get_list_financial_statements_sub(v1,names_flag) {
																			let data = {};
								let flag = false;
												await this.$get(
					"~/api/financial_statements/get_list?groupby=statistical_date",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(this.$toTime(list[i].statistical_date ,"yyyy-MM-dd"));
											}
							this.line_obj_financial_statements.xAxis = xAxis_list;
							this.get_list_financial_statements_sub_sub(v1,"statistical_date",names_flag,flag);
						}
				});
																							},
			async get_list_financial_statements_sub_sub(v1,v2,names_flag,xAxis_flag) {
																													let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								for (let i=0;i<this.line_obj_financial_statements.xAxis.length;i++){
					// let list = []
					this.$set(this.line_obj_financial_statements.values, i, []);
					for (let j=0;j<this.line_obj_financial_statements.names.length;j++){
						data[v2] = this.line_obj_financial_statements.xAxis[i];
						data[v1] = this.line_obj_financial_statements.names[j];
						await this.$get(
								"~/api/financial_statements/sum?field=statistical_amount",
								data,
								(json) => {
									if (json.result) {
										// list[j] = json.result;
										this.$set(this.line_obj_financial_statements.values[i], j, json.result);
									}else {
										// list[j] = 0;
										this.$set(this.line_obj_financial_statements.values[i], j, 0);
									}
								});
					}
									}
				if (names_flag){
					this.get_nickname(this.line_obj_financial_statements.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_financial_statements.xAxis,true);
				}
													},
	
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
