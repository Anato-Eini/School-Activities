import {ajax_comment_vote, ajax_post_vote} from "./functions.js";

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })

$(document).ready(() => {

    /**
     * Initialization of votes in comments
     */
    $('.comment-vote-counts').each(function () {
        let child = $(this)

        let comment_id = child.find('button').first().data('comment-id'); 

        let upvote_element = child.find('.comment-upvote');
        let downvote_element = child.find('.comment-downvote');

        ajax_comment_vote(1, comment_id, "GET")
            .then(data => {
                upvote_element.text("Upvote: ".concat(data[0]))
                downvote_element.text("Downvote: ".concat(data[1]))
            }).catch(error => console.error("Error fetching comment votes ", error))

        upvote_element.on('click', () => {
            ajax_comment_vote(1, comment_id, "POST")
                .then(data => {
                    upvote_element.text("Upvote: ".concat(data[0]))
                    downvote_element.text("Downvote: ".concat(data[1]))
                }).catch(error => console.error("Error upvoting comment ", error))
        })

        downvote_element.on('click', () => {
            ajax_comment_vote(0, comment_id, "POST")
                .then(data => {
                    upvote_element.text("Upvote: ".concat(data[0]))
                    downvote_element.text("Downvote: ".concat(data[1]))
                }).catch(error => console.error("Error downvoting comment ", error))
        })
    })

    $('#post_upvote').on('click', () => {
        let upvote_div = $('#post_upvote')
        if(upvote_div.hasClass('upvoted'))
            upvote_div.removeClass('upvoted')
        else
            upvote_div.addClass('upvoted')

        ajax_post_vote(1, user_post_pk, post_vote_url)
            .then(data => {
                $('#post-upvote-count').text(data[0])
                $('#post-downvote-count').text(data[1])
            }).catch(error => {
                console.error("Error upvoting post ", error)
            })
    })

    $('#post_downvote').on('click', () => {
        ajax_post_vote(0, user_post_pk, post_vote_url)
            .then(data => {
                $('#post-upvote-count').text(data[0])
                $('#post-downvote-count').text(data[1])
            }).catch(error => {
                console.error("Error downvoting post ", error)
            })

        let downvote_div = $('#post_downvote')
        if(downvote_div.hasClass('downvoted'))
            downvote_div.removeClass('downvoted')
        else
            downvote_div.addClass('downvoted')
    })
})
