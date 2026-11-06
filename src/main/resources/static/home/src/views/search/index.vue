<template>
  <div class="page_search search_index">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="健身资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/member_user/list', 'get')"
				:list="result_member_user_member_name"
				title="会员用户会员姓名"
				source_table="member_user"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/member_user/list', 'get')"
				:list="result_member_user_membership_type"
				title="会员用户会籍类型"
				source_table="member_user"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/course_information/list', 'get')"
				:list="result_course_information_course_name"
				title="课程信息课程名称"
				source_table="course_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/course_information/list', 'get')"
				:list="result_course_information_course_type"
				title="课程信息课程类型"
				source_table="course_information"
			  ></list_result_search>
																											  <list_result_search
				v-if="$check_action('/course_participation/list', 'get')"
				:list="result_course_participation_course_name"
				title="课程参与课程名称"
				source_table="course_participation"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/course_participation/list', 'get')"
				:list="result_course_participation_course_type"
				title="课程参与课程类型"
				source_table="course_participation"
			  ></list_result_search>
																														  <list_result_search
				v-if="$check_action('/fitness_advice/list', 'get')"
				:list="result_fitness_advice_member_name"
				title="健身建议会员姓名"
				source_table="fitness_advice"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/fitness_advice/list', 'get')"
				:list="result_fitness_advice_suggested_title"
				title="健身建议建议标题"
				source_table="fitness_advice"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/membership_card/list', 'get')"
				:list="result_membership_card_membership_card_name"
				title="会员卡会员卡名称"
				source_table="membership_card"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/membership_card/list', 'get')"
				:list="result_membership_card_membership_card_type"
				title="会员卡会员卡类型"
				source_table="membership_card"
			  ></list_result_search>
																											  <list_result_search
				v-if="$check_action('/device_information/list', 'get')"
				:list="result_device_information_equipment_name"
				title="设备信息设备名称"
				source_table="device_information"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/device_information/list', 'get')"
				:list="result_device_information_equipment_type"
				title="设备信息设备类型"
				source_table="device_information"
			  ></list_result_search>
																											  <list_result_search
				v-if="$check_action('/financial_statements/list', 'get')"
				:list="result_financial_statements_report_name"
				title="财务报表报表名称"
				source_table="financial_statements"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/financial_statements/list', 'get')"
				:list="result_financial_statements_report_type"
				title="财务报表报表类型"
				source_table="financial_statements"
			  ></list_result_search>
																		</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_member_user_member_name":[],
											"result_member_user_membership_type":[],
															"result_course_information_course_name":[],
								"result_course_information_course_type":[],
																											"result_course_participation_course_name":[],
								"result_course_participation_course_type":[],
																														"result_fitness_advice_member_name":[],
								"result_fitness_advice_suggested_title":[],
															"result_membership_card_membership_card_name":[],
								"result_membership_card_membership_card_type":[],
																											"result_device_information_equipment_name":[],
								"result_device_information_equipment_type":[],
																											"result_financial_statements_report_name":[],
								"result_financial_statements_report_type":[],
																};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取member_name
	 */
	get_member_user_member_name(){
		let url = "~/api/member_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "member_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_member_user_member_name = json.result.list;
			result_member_user_member_name.map(o => o.title = o['member_name'])
	  			this.result_member_user_member_name = result_member_user_member_name
		 	}
		});
	},
									/**
	 * 获取membership_type
	 */
	get_member_user_membership_type(){
		let url = "~/api/member_user/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "membership_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_member_user_membership_type = json.result.list;
			result_member_user_membership_type.map(o => o.title = o['membership_type'])
	  			this.result_member_user_membership_type = result_member_user_membership_type
		 	}
		});
	},
													/**
	 * 获取course_name
	 */
	get_course_information_course_name(){
		let url = "~/api/course_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_information_course_name = json.result.list;
			result_course_information_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_information_course_name = result_course_information_course_name
		 	}
		});
	},
						/**
	 * 获取course_type
	 */
	get_course_information_course_type(){
		let url = "~/api/course_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "course_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_information_course_type = json.result.list;
			result_course_information_course_type.map(o => o.title = o['course_type'])
	  			this.result_course_information_course_type = result_course_information_course_type
		 	}
		});
	},
																									/**
	 * 获取course_name
	 */
	get_course_participation_course_name(){
		let url = "~/api/course_participation/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_participation_course_name = json.result.list;
			result_course_participation_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_participation_course_name = result_course_participation_course_name
		 	}
		});
	},
						/**
	 * 获取course_type
	 */
	get_course_participation_course_type(){
		let url = "~/api/course_participation/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "course_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_participation_course_type = json.result.list;
			result_course_participation_course_type.map(o => o.title = o['course_type'])
	  			this.result_course_participation_course_type = result_course_participation_course_type
		 	}
		});
	},
																												/**
	 * 获取member_name
	 */
	get_fitness_advice_member_name(){
		let url = "~/api/fitness_advice/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "member_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_fitness_advice_member_name = json.result.list;
			result_fitness_advice_member_name.map(o => o.title = o['member_name'])
	  			this.result_fitness_advice_member_name = result_fitness_advice_member_name
		 	}
		});
	},
						/**
	 * 获取suggested_title
	 */
	get_fitness_advice_suggested_title(){
		let url = "~/api/fitness_advice/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "suggested_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_fitness_advice_suggested_title = json.result.list;
			result_fitness_advice_suggested_title.map(o => o.title = o['suggested_title'])
	  			this.result_fitness_advice_suggested_title = result_fitness_advice_suggested_title
		 	}
		});
	},
													/**
	 * 获取membership_card_name
	 */
	get_membership_card_membership_card_name(){
		let url = "~/api/membership_card/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "membership_card_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_membership_card_membership_card_name = json.result.list;
			result_membership_card_membership_card_name.map(o => o.title = o['membership_card_name'])
	  			this.result_membership_card_membership_card_name = result_membership_card_membership_card_name
		 	}
		});
	},
						/**
	 * 获取membership_card_type
	 */
	get_membership_card_membership_card_type(){
		let url = "~/api/membership_card/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "membership_card_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_membership_card_membership_card_type = json.result.list;
			result_membership_card_membership_card_type.map(o => o.title = o['membership_card_type'])
	  			this.result_membership_card_membership_card_type = result_membership_card_membership_card_type
		 	}
		});
	},
																									/**
	 * 获取equipment_name
	 */
	get_device_information_equipment_name(){
		let url = "~/api/device_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "equipment_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_device_information_equipment_name = json.result.list;
			result_device_information_equipment_name.map(o => o.title = o['equipment_name'])
	  			this.result_device_information_equipment_name = result_device_information_equipment_name
		 	}
		});
	},
						/**
	 * 获取equipment_type
	 */
	get_device_information_equipment_type(){
		let url = "~/api/device_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "equipment_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_device_information_equipment_type = json.result.list;
			result_device_information_equipment_type.map(o => o.title = o['equipment_type'])
	  			this.result_device_information_equipment_type = result_device_information_equipment_type
		 	}
		});
	},
																									/**
	 * 获取report_name
	 */
	get_financial_statements_report_name(){
		let url = "~/api/financial_statements/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "report_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_financial_statements_report_name = json.result.list;
			result_financial_statements_report_name.map(o => o.title = o['report_name'])
	  			this.result_financial_statements_report_name = result_financial_statements_report_name
		 	}
		});
	},
						/**
	 * 获取report_type
	 */
	get_financial_statements_report_type(){
		let url = "~/api/financial_statements/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "report_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_financial_statements_report_type = json.result.list;
			result_financial_statements_report_type.map(o => o.title = o['report_type'])
	  			this.result_financial_statements_report_type = result_financial_statements_report_type
		 	}
		});
	},
															
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_member_user_member_name();
										this.get_member_user_membership_type();
														this.get_course_information_course_name();
							this.get_course_information_course_type();
																										this.get_course_participation_course_name();
							this.get_course_participation_course_type();
																													this.get_fitness_advice_member_name();
							this.get_fitness_advice_suggested_title();
														this.get_membership_card_membership_card_name();
							this.get_membership_card_membership_card_type();
																										this.get_device_information_equipment_name();
							this.get_device_information_equipment_type();
																										this.get_financial_statements_report_name();
							this.get_financial_statements_report_type();
															  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_member_user_member_name();
									  this.get_member_user_membership_type();
													  this.get_course_information_course_name();
						  this.get_course_information_course_type();
																									  this.get_course_participation_course_name();
						  this.get_course_participation_course_type();
																												  this.get_fitness_advice_member_name();
						  this.get_fitness_advice_suggested_title();
													  this.get_membership_card_membership_card_name();
						  this.get_membership_card_membership_card_type();
																									  this.get_device_information_equipment_name();
						  this.get_device_information_equipment_type();
																									  this.get_financial_statements_report_name();
						  this.get_financial_statements_report_type();
																},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
