//This file contains functions that are used in the blog app

//This function is used to upvote or downvote a post given a user_post_id
export function get_views(user_post_id){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/view_num/',
            method: "GET",
            data: {
                'pk': user_post_id,
            },
            headers: {
                'X-CSRFToken' : csrf_token
            },
            success: (data) => resolve(data.view_count),
            error: (error) => reject(error)
        })
    })
}

//This function is used to get the number of comments for a post given a user_post_id
export function get_comments_count(user_post_id){
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/comment_num/',
            method: "GET",
            data:{
                'pk': user_post_id
            },
            headers: {
                'X-CSRFToken' : csrf_token
            },
            success: (data) => {
                resolve(data.comment_count)
            },
            error: (error) => {
                reject(error)
            }
        })
    })
}

/**
 * Handles post_vote ajax requests
 * @param type - Vote type 1-Upvote 2-Downvote
 * @param pk - PostUser id
 * @param url - Url for ajax
 */
export function ajax_post_vote(type, pk, url) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            method: "POST",
            data: {
                type: type,
                pk: pk
            },
            headers: {
                'X-CSRFToken': csrf_token
            },
            success: (data) => {
                resolve([data.upvote, data.downvote])
            },
            error: (error) => {
                reject(error)
            }
        })
    })
}

/**
 * Fetch data from the server and update the upvote and downvote counts of the comment
 * @param type - 1-Upvote 2-Downvote
 * @param pk - Comment.id
 * @param method - "POST" or "GET"
 * @param resolve_element - index[0] - upvote value, index[1] - downvote value
 * @param reject_element - error
 */
export function ajax_comment_vote(type, pk, method) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: comment_vote_url,
            method: method,
            data: {
                pk: pk,
                type: type
            },
            headers: {
                'X-CSRFToken': csrf_token
            },
            success: (data) => resolve([data.upvote, data.downvote]),
            error: (error) => reject(error)
        })
    })
}
