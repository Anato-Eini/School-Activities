from django.contrib import admin
from .models import *


@admin.register(PostView)
class PostViewAdmin(admin.ModelAdmin):
    """
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    """
    list_display = (
        'id',
        'user_ref',
        'user_post_ref'
    )


@admin.register(Post)
class PostAdmin(admin.ModelAdmin):
    """
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.CharField(max_length=100)
    content = models.TextField()
    created_at = models.DateField(auto_now_add=True)
    """
    list_display = (
        'id',
        'user_ref',
        'title',
        'content',
        'created_at'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(Forum)
class ForumAdmin(admin.ModelAdmin):
    """
    title = models.CharField(max_length=100)
    description = models.TextField()
    """
    list_display = (
        'id',
        'title',
        'description'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(Comment)
class CommentAdmin(admin.ModelAdmin):
    """
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    image = models.ImageField(upload_to=upload_path, blank=True, null=True)
    """
    list_display = (
        'id',
        'user_post_ref',
        'content',
        'created_at',
        'image',
        'user_ref'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(User)
class UserAdmin(admin.ModelAdmin):
    """
    username = models.CharField(max_length=100, unique=True)
    password = models.CharField(max_length=255)
    email = models.EmailField()
    birth_date = models.DateField()
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    user_type = models.IntegerField() #Admin = 0, user = 1
    """
    list_display = (
        'id',
        'username',
        'password',
        'picture'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(UserPost)
class UserPostAdmin(admin.ModelAdmin):
    """
    post_ref = models.ForeignKey(Post, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    """
    list_display = (
        'id',
        'post_ref',
        'user_ref',
        'is_deleted'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(VotePost)
class VotePostAdmin(admin.ModelAdmin):
    """
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    is_upvote = models.BooleanField()
    """
    list_display = (
        'id',
        'user_post_ref',
        'is_upvote'
    )

    def delete_model(self, request, obj):
        super().delete_model(request, obj)


@admin.register(VoteComment)
class VoteCommentAdmin(admin.ModelAdmin):
    """
    comment_ref = models.ForeignKey(Comment, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    is_upvote = models.BooleanField()
    """
    list_display = (
        'id',
        'comment_ref',
        'user_ref',
        'is_upvote'
    )


@admin.register(FavoritePost)
class FavoritePostAdmin(admin.ModelAdmin):
    """
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    """
    list_display = (
        'id',
        'user_ref',
        'user_post_ref'
    )


@admin.register(Tag)
class TagAdmin(admin.ModelAdmin):
    """
    forum_ref = models.ForeignKey(Forum, on_delete=models.CASCADE)
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    """
    list_display = (
        'id',
        'forum_ref',
        'user_post_ref'
    )


@admin.register(ReportPost)
class ReportPostAdmin(admin.ModelAdmin):
    """
    post_ref = models.ForeignKey(Post, on_delete=models.CASCADE)
    reason = models.TextField()
    """
    list_display = (
        'id',
        'post_ref',
        'user_ref'
    )


@admin.register(ReportComment)
class ReportCommentAdmin(admin.ModelAdmin):
    """
    comment_ref = models.ForeignKey(Comment, on_delete=models.CASCADE)
    reason = models.TextField()
    """
    list_display = (
        'id',
        'comment_ref',
        'user_ref'
    )


@admin.register(UserBan)
class UserBanReport(admin.ModelAdmin):
    """
   admin_ref = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        related_name='bans_issued'
    )
    user_ref = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        related_name='bans_received'
    )
    reason = models.TextField()
    time = models.DateTimeField(auto_now_add=True)
    """
    list_display = (
        'id',
        'admin_ref',
        'user_ref',
        'reason',
        'time'
    )


@admin.register(BanAppeal)
class BanAppealAdmin(admin.ModelAdmin):
    """
    userban_ref = models.ForeignKey(UserBan, on_delete=models.CASCADE)
    justification = models.TextField()
    time = models.DateTimeField(auto_now_add=True)
    """
    list_display = (
        'userban_ref',
        'justification',
        'time'
    )
