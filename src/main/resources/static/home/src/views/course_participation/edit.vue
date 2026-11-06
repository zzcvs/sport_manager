<template>
	<div class="diy_edit page_course_participation" id="course_participation_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','course_name') || $check_field('add','course_name') || $check_field('get','course_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								课程名称:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_course_name" v-model="form['course_name']" placeholder="请输入课程名称" v-if="(form['course_name'] && $check_field('set','course_name')) || (!form['course_name'] && $check_field('add','course_name'))"  :disabled="disabledObj['course_name_isDisabled']"/>
							<span v-else-if="$check_field('get','course_name')">{{ form['course_name'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','course_type') || $check_field('add','course_type') || $check_field('get','course_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								课程类型:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_course_type" v-model="form['course_type']" placeholder="请输入课程类型" v-if="(form['course_type'] && $check_field('set','course_type')) || (!form['course_type'] && $check_field('add','course_type'))"  :disabled="disabledObj['course_type_isDisabled']"/>
							<span v-else-if="$check_field('get','course_type')">{{ form['course_type'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','course_time') || $check_field('add','course_time') || $check_field('get','course_time')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								课程时间:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_course_time" v-model="form['course_time']" placeholder="请输入课程时间" v-if="(form['course_time'] && $check_field('set','course_time')) || (!form['course_time'] && $check_field('add','course_time'))"  :disabled="disabledObj['course_time_isDisabled']"/>
							<span v-else-if="$check_field('get','course_time')">{{ form['course_time'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','course_location') || $check_field('add','course_location') || $check_field('get','course_location')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								课程地点:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_course_location" v-model="form['course_location']" placeholder="请输入课程地点" v-if="(form['course_location'] && $check_field('set','course_location')) || (!form['course_location'] && $check_field('add','course_location'))"  :disabled="disabledObj['course_location_isDisabled']"/>
							<span v-else-if="$check_field('get','course_location')">{{ form['course_location'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','name_of_coach') || $check_field('add','name_of_coach') || $check_field('get','name_of_coach')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								教练姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_name_of_coach" v-model="form['name_of_coach']" placeholder="请输入教练姓名" v-if="(form['name_of_coach'] && $check_field('set','name_of_coach')) || (!form['name_of_coach'] && $check_field('add','name_of_coach'))"  :disabled="disabledObj['name_of_coach_isDisabled']"/>
							<span v-else-if="$check_field('get','name_of_coach')">{{ form['name_of_coach'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','member_user') || $check_field('add','member_user') || $check_field('get','member_user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								会员用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_member_user" :disabled="disabledObj['member_user_isDisabled']" v-model="form['member_user']" v-if="(form['member_user'] && $check_field('set','member_user')) || (!form['member_user'] && $check_field('add','member_user'))" >
								<option v-for="o in list_user_member_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','member_user')">{{ get_user_info("member_user", form['member_user']) }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','member_name') || $check_field('add','member_name') || $check_field('get','member_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								会员姓名:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_member_name" v-model="form['member_name']" placeholder="请输入会员姓名" v-if="(form['member_name'] && $check_field('set','member_name')) || (!form['member_name'] && $check_field('add','member_name'))"  :disabled="disabledObj['member_name_isDisabled']"/>
							<span v-else-if="$check_field('get','member_name')">{{ form['member_name'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','participation_notes') || $check_field('add','participation_notes') || $check_field('get','participation_notes')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								参与备注:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_participation_notes" v-model="form['participation_notes']" v-if="(form['participation_notes'] && $check_field('set','participation_notes')) || (!form['participation_notes'] && $check_field('add','participation_notes'))" :disabled="disabledObj['participation_notes_isDisabled']" />
							<span v-else-if="$check_field('get','participation_notes')">{{ form['participation_notes'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/course_participation/get_obj?",
				url_add: "~/api/course_participation/add?",
				url_set: "~/api/course_participation/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"course_name": "",
							"course_type": "",
							"course_time": "",
							"course_location": "",
							"name_of_coach": "",
							"member_user": 0,
							"member_name": "",
							"participation_notes": "",
						"course_participation_id": 0,
				},

				obj: {
						"course_name":  '', // 课程名称
							"course_type":  '', // 课程类型
							"course_time":  '', // 课程时间
							"course_location":  '', // 课程地点
							"name_of_coach":  '', // 教练姓名
							"member_user": 0, // 会员用户
							"member_name":  '', // 会员姓名
							"participation_notes":  '', // 参与备注
						"course_participation_id": 0,
				},

				// 表单字段
				form: {
						"course_name":  '', // 课程名称
							"course_type":  '', // 课程类型
							"course_time":  '', // 课程时间
							"course_location":  '', // 课程地点
							"name_of_coach":  '', // 教练姓名
							"member_user": 0, // 会员用户
							"member_name":  '', // 会员姓名
							"participation_notes":  '', // 参与备注
						"course_participation_id": 0,
				},
				disabledObj:{
						"course_name_isDisabled": false,
							"course_type_isDisabled": false,
							"course_time_isDisabled": false,
							"course_location_isDisabled": false,
							"name_of_coach_isDisabled": false,
							"member_user_isDisabled": false,
							"member_name_isDisabled": false,
							"participation_notes_isDisabled": false,
					},

																					// 用户列表
				list_user_member_user: [],
								
				// ID字段
				field: "course_participation_id",

			}
		},
		methods: {
																																																																						formatDateValue(value) {
		  const date = new Date(value);
		  return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
		},
		async submit(param, func){
			if (!param) {
				param = this.form;
			}
						var pm = this.events("submit_before", Object.assign({}, param)) || param;
			var msg = await this.events("submit_check", pm);
			var ret;
			if (msg) {
				this.$toast(msg, 'danger');
			} else {
																													ret = this.events("submit_main", pm, func);
			}
			return ret;
		},
		
      /**
       * 提交前验证事件
       * @param {Object} 请求参数
       * @return {String} 验证成功返回null, 失败返回错误提示
       */
            submit_check(param) {
      																																								        return null;
      },
			
				
				
				
				
						/**
			 * 获取会员用户用户列表
			 */
			async get_list_user_member_user() {
				var json = await this.$get("~/api/user/get_list?user_group=会员用户");
				if(json.result && json.result.list){
					this.list_user_member_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
					async get_user_session_member_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=会员用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["member_user"] = user_id
								_this.disabledObj['member_user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "member_user") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
		  		get_user_info(name,id){
				var obj = null;
            				  if (name == 'member_user'){
					  obj = this.list_user_member_user.getObj({"user_id":id});
				  }
      				var ret = "";
				if(obj){
				  ret = obj.nickname+"-"+obj.username;
				}
				return ret;
			},
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/course_participation/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},
			
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file_multiple(files, str) {
				let _this = this;
				var form = new FormData();
				for (var i = 0; i < files.length; i++) {
					form.set("file", files[i]);
					_this.$post("~/api/course_participation/upload?", form, (res) => {
						if (res.result) {
							if (_this.form[str].length > 0) {
								_this.form[str].push(res.result.url);
							} else {
								_this.form[str] = [res.result.url];
							}
						} else if (res.error) {
							_this.$toast(res.error.message);
						}
					});
				}
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
			  if(arr[i] === "source_table"){
			  	this.form['source_table'] = form[arr[i]]
			  }
			  if(arr[i] === "source_id"){
			  	this.form['source_id'] = form[arr[i]]
			  }
			  if(arr[i] === "source_user_id"){
			  	this.form['source_user_id'] = form[arr[i]]
			  }
            }
          }
        }
																								
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
																				this.get_user_session_member_user();
					this.get_list_user_member_user();
									},
	}
</script>

<style>
	.diy_compute{
		line-height: 40px;
	}
	.diy_field.diy_img_multiple{
		margin: 0;
	}
	.diy_field.diy_img_multiple div{
		float: left;
		position: relative;
		margin: 0 10px 10px 0;
	}
	.diy_field.diy_img_multiple img{
		height: 100px;
		width: auto;
	}
	.diy_field.diy_img_multiple span{
		position: absolute;
		top: 5px;
		right: 5px;
		width: 20px;
		height: 20px;
		background: #0000008a;
		color: #fff;
		line-height: 20px;
		text-align: center;
		border-radius: 100%;
		cursor: pointer;
	}



</style>
