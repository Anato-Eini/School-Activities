import { get_views, get_comments_count } from './functions.js'

$(document).ready(function () {

    document.getElementById('showOverview').onclick = function() {
        document.getElementById('OverviewDashboard').style.display = 'block';
        document.getElementById('FavoritesDashboard').style.display = 'none';
        document.getElementById('DeleteDashboard').style.display = 'none';
    };

    document.getElementById('showFavorites').onclick = function() {
        document.getElementById('FavoritesDashboard').style.display = 'block';
        document.getElementById('OverviewDashboard').style.display = 'none';
        document.getElementById('DeleteDashboard').style.display = 'none';
    };

    document.getElementById('showDeleted').onclick = function() {
        document.getElementById('DeleteDashboard').style.display = 'block';
        document.getElementById('FavoritesDashboard').style.display = 'none';
        document.getElementById('OverviewDashboard').style.display = 'none';
    };

    $('.favorite-views').each(function () {
        let this_element = $(this);
        get_views(this_element.data('value')).then(view_count => {
            this_element.append(" ".concat(view_count))
        }).catch(error => {
            console.error("Error fetching view count ", error)
        })
    })
    $('.favorite-comments-num').each(function () {
        let this_element = $(this);
        get_comments_count(this_element.data('value')).then(count => {
            this_element.append(" ".concat(count))
        }).catch(error => {
            console.error("Error fetching comments count", error)
        })
    })
})
