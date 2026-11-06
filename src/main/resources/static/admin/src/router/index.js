import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
    // 主页
    {
        path: '/',
        name: 'index',
        component: index,
        meta: {
            index: 0,
            title: '首页'
        }
    },

    // 登录
    {
        path: '/login',
        name: 'login',
        component: login,
        meta: {
            index: 0,
            title: '登录'
        }
    },

    	
	
    // 忘记密码
    {
        path: '/forgot',
        name: "forgot",
        component: forgot,
        meta: {
            index: 0,
            title: '忘记密码'
        }
    },

    // 修改密码
    {
        path: '/user/password',
        name: "password",
        component: () => import("../views/user/password.vue"),
        meta: {
            index: 0,
            title: '修改密码'
        }
    },

    // 视频播放页
    {
        path: "/media/video",
        name: "video",
        component: () => import('../views/media/video.vue'),
        meta: {
            index: 0,
            title: "视频"
        }
    },

    // 音频播放页
    {
        path: "/media/audio",
        name: "audio",
        component: () => import('../views/media/audio.vue'),
        meta: {
            index: 0,
            title: "音频"
        }
    },

    
    
                // 轮播图路由
        {
            path: '/slides/table',
            name: 'slides_table',
            component: () => import('../views/slides/table.vue'),
            meta: {
                index: 0,
                title: '轮播图列表'
            }
        },
        {
            path: '/slides/view',
            name: 'slides_view',
            component: () => import('../views/slides/view.vue'),
            meta: {
                index: 0,
                title: '轮播图详情'
            }
        },
                    // 文章路由
            {
                path: '/article/table',
                name: 'article_table',
                component: () => import('../views/article/table.vue'),
                meta: {
                    index: 0,
                    title: '健身资讯列表'
                }
            },
            {
                path: '/article/view',
                name: 'article_view',
                component: () => import('../views/article/view.vue'),
                meta: {
                    index: 0,
                    title: '健身资讯详情'
                }
            },

            // 文章分类路由
            {
                path: '/article_type/table',
                name: 'article_type_table',
                component: () => import('../views/article_type/table.vue'),
                meta: {
                    index: 0,
                    title: '健身资讯分类列表'
                }
            },
            {
                path: '/article_type/view',
                name: 'article_type_view',
                component: () => import('../views/article_type/view.vue'),
                meta: {
                    index: 0,
                    title: '健身资讯分类详情'
                }
            },
                            
    
    
            // 公告路由
        {
            path: '/notice/table',
            name: 'notice_table',
            component: () => import('../views/notice/table.vue'),
            meta: {
                index: 0,
                title: '网站公告列表'
            }
        },
        {
            path: '/notice/view',
            name: 'notice_view',
            component: () => import('../views/notice/view.vue'),
            meta: {
                index: 0,
                title: '网站公告详情'
            }
        },
            	    
            // 评论路由
        {
            path: '/comment/table',
            name: 'comment_table',
            component: () => import('../views/comment/table.vue'),
            meta: {
                index: 0,
                title: '评论列表'
            }
        },
        {
            path: '/comment/view',
            name: 'comment_view',
            component: () => import('../views/comment/view.vue'),
            meta: {
                index: 0,
                title: '评论详情'
            }
        },
        	            // 会员用户路由
        {
            path: '/member_user/table',
            name: 'member_user_table',
            component: () => import('../views/member_user/table.vue'),
            meta: {
                index: 0,
                title: '会员用户列表'
            }
        },
        {
            path: '/member_user/view',
            name: 'member_user_view',
            component: () => import('../views/member_user/view.vue'),
            meta: {
                index: 0,
                title: '会员用户详情'
            }
        },
						            // 课程信息路由
        {
            path: '/course_information/table',
            name: 'course_information_table',
            component: () => import('../views/course_information/table.vue'),
            meta: {
                index: 0,
                title: '课程信息列表'
            }
        },
        {
            path: '/course_information/view',
            name: 'course_information_view',
            component: () => import('../views/course_information/view.vue'),
            meta: {
                index: 0,
                title: '课程信息详情'
            }
        },
						            // 课程参与路由
        {
            path: '/course_participation/table',
            name: 'course_participation_table',
            component: () => import('../views/course_participation/table.vue'),
            meta: {
                index: 0,
                title: '课程参与列表'
            }
        },
        {
            path: '/course_participation/view',
            name: 'course_participation_view',
            component: () => import('../views/course_participation/view.vue'),
            meta: {
                index: 0,
                title: '课程参与详情'
            }
        },
						            // 健身建议路由
        {
            path: '/fitness_advice/table',
            name: 'fitness_advice_table',
            component: () => import('../views/fitness_advice/table.vue'),
            meta: {
                index: 0,
                title: '健身建议列表'
            }
        },
        {
            path: '/fitness_advice/view',
            name: 'fitness_advice_view',
            component: () => import('../views/fitness_advice/view.vue'),
            meta: {
                index: 0,
                title: '健身建议详情'
            }
        },
						            // 会员卡路由
        {
            path: '/membership_card/table',
            name: 'membership_card_table',
            component: () => import('../views/membership_card/table.vue'),
            meta: {
                index: 0,
                title: '会员卡列表'
            }
        },
        {
            path: '/membership_card/view',
            name: 'membership_card_view',
            component: () => import('../views/membership_card/view.vue'),
            meta: {
                index: 0,
                title: '会员卡详情'
            }
        },
						            // 设备信息路由
        {
            path: '/device_information/table',
            name: 'device_information_table',
            component: () => import('../views/device_information/table.vue'),
            meta: {
                index: 0,
                title: '设备信息列表'
            }
        },
        {
            path: '/device_information/view',
            name: 'device_information_view',
            component: () => import('../views/device_information/view.vue'),
            meta: {
                index: 0,
                title: '设备信息详情'
            }
        },
						            // 财务报表路由
        {
            path: '/financial_statements/table',
            name: 'financial_statements_table',
            component: () => import('../views/financial_statements/table.vue'),
            meta: {
                index: 0,
                title: '财务报表列表'
            }
        },
        {
            path: '/financial_statements/view',
            name: 'financial_statements_view',
            component: () => import('../views/financial_statements/view.vue'),
            meta: {
                index: 0,
                title: '财务报表详情'
            }
        },
						    	    // 用户路由
    {
        path: '/user/table',
        name: 'user_table',
        component: () => import('../views/user/table.vue'),
        meta: {
            index: 0,
            title: '用户列表'
        }
    },
    {
        path: '/user/view',
        name: 'user_view',
        component: () => import('../views/user/view.vue'),
        meta: {
            index: 0,
            title: '用户详情'
        }
    },
    {
        path: '/user/info',
        name: 'user_info',
        component: () => import('../views/user/info.vue'),
        meta: {
            index: 0,
            title: '个人信息'
        }
    },
    // 用户组路由
    {
        path: '/user_group/table',
        name: 'user_group_table',
        component: () => import('../views/user_group/table.vue'),
        meta: {
            index: 0,
            title: '用户组列表'
        }
    },
    {
        path: '/user_group/view',
        name: 'user_group_view',
        component: () => import('../views/user_group/view.vue'),
        meta: {
            index: 0,
            title: '用户组详情'
        }
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    let token = to.query.token;
    if (token) {
        $.db.set("token", token, 120);
    }
    next();
})

router.afterEach((to, from, next) => {
    let title = "健身房管理系统-admin";
    document.title = title;
})

export default router
