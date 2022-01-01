// 用户
let user = {
    id: 1,
    name: '张三',
    // 基于用户身份的角色信息
    role: [
        role1,
        role2,
    ]
}

// 角色表
let role1 = {
    id: 1,
    name: '注册用户', // 部长
    enable: true
}

let role2 = {
    id: 2,
    name: '系统管理员',  // 主任
    enable: true
}

user.role.push(role1, role2);



// 角色-用户关联表
let role_user = [

    {        id: 1,        user_id: 1,        role_id: 1    },
    {        id: 2,        user_id: 1,        role_id: 2    },

    {        id: 3,        user_id: 2,        role_id: 1    },
    {        id: 4,        user_id: 2,        role_id: 2    },
    {        id: 5,        user_id: 3,        role_id: 1    },

]