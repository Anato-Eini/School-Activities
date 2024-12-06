from django.contrib.auth.models import BaseUserManager, AbstractBaseUser, PermissionsMixin
from django.db import models


class UserManager(BaseUserManager):
    """
    Custom manager model
    """

    def create_user(self, username, password, **extra_fields):
        """
        Creates and returns a User with an encrypted password

        Parameters:
            username (str): User's username
            password (str): User's password
            **extra_fields (dict): Extra fields to pass to the model

        Returns:
            User: The created user instance.

        Raises:
            ValueError: If no username or password is provided.
        """
        if not username or not password:
            raise ValueError('Incomplete credentials provided.')
        user = self.model(username=username, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, username, password, **extra_fields):
        """
        Creates and returns a superuser with the given username and password.
        Parameters:
            username (str): The username of the superuser
            password (str): The password of the superuser

        Returns:
            User: The created superuser instance.
        """
        extra_fields.setdefault('is_superuser', True)
        extra_fields.setdefault('is_staff', True)
        return self.create_user(username, password, **extra_fields)


class User(AbstractBaseUser, PermissionsMixin):
    """
    Custom User model that uses username and password for authentication
    """
    username = models.CharField(max_length=100, unique=True)
    picture = models.ImageField(
        upload_to='profile/',
        default='profile/blank-profile-picture-973460_128012234212.png',
        blank=True,
    )
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    is_banned = models.BooleanField(default=False)

    USERNAME_FIELD = 'username'

    objects = UserManager()

    def __str__(self):
        """
        Returns a string representation of the user.

        Returns:
            str: The username of the user.
        """
        return self.username


class Forum(models.Model):
    """
    Represents a forum where discussions take place
    """
    title = models.CharField(max_length=100)
    description = models.TextField()

    def __str__(self):
        """
        Returns the string representation of the Forum instance.

        Returns:
            str: The title of the forum.
        """
        return self.title


def upload_path(instance, filename):
    """
    Generates the upload path for the image.

    Args:
        instance: The model instance.
        filename (str): The name of the file being uploaded.

    Returns:
        str: The upload path for the image.
    """
    return f"{filename}"


class Post(models.Model):
    """
    Represents a post within a forum created by a user.
    """
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.CharField(max_length=100)
    content = models.TextField(blank=True, null=True)
    created_at = models.DateField(auto_now_add=True)
    image = models.ImageField(upload_to=upload_path, blank=True, null=True)

    def __str__(self):
        """
        Returns the string representation of the Post instance.

        Returns:
            str: The title of the post.
        """
        return self.title


class UserPost(models.Model):
    """
    Represents the relationship between a user and a post they interact with.
    """
    post_ref = models.ForeignKey(Post, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    is_deleted = models.BooleanField(default=False)


class User_UserPost(models.Model):
    """
    Connect UserPost and User
    """
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)

    class Meta:
        abstract = True


class VotePost(User_UserPost):
    """
    Represents a vote on a post by a user.
    """
    is_upvote = models.BooleanField()


class Comment(User_UserPost):
    """
    Represents a comment made by a user on a specific user post.
    """
    content = models.TextField(blank=True, null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    image = models.ImageField(upload_to=upload_path, blank=True, null=True)

    def __str__(self):
        """
        Returns a string representation of the Comment instance.

        Returns:
            str: The username of the commenter and the title of the post.
        """
        return f"{self.user_ref.username} on {self.user_post_ref.post_ref.title}"


class VoteComment(models.Model):
    """
    Represents a vote on a comment by a user.
    """
    comment_ref = models.ForeignKey(Comment, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE)
    is_upvote = models.BooleanField()


class PostView(User_UserPost):
    """
    Represents a posts viewed by users.
    """


class FavoritePost(User_UserPost):
    """
    Represents the favorite post marked by the user.
    """


class Tag(models.Model):
    """
    Tags (Forums) for posts
    """
    forum_ref = models.ForeignKey(Forum, on_delete=models.CASCADE)
    user_post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)


class UserBan(models.Model):
    """
    Represents a ban on a user by an admin.
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


class BanAppeal(models.Model):
    """
    Represents a ban appeal of a banned user in order to try to get unbanned by an admin.
    """
    userban_ref = models.ForeignKey(UserBan, on_delete=models.CASCADE)
    justification = models.TextField()
    time = models.DateTimeField(auto_now_add=True)


class ReportPost(models.Model):
    """
    Represents a report on a post by a user.
    """
    post_ref = models.ForeignKey(UserPost, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    reason = models.TextField()


class ReportComment(models.Model):
    """
    Represents a report on a comment by a user.
    """
    comment_ref = models.ForeignKey(Comment, on_delete=models.CASCADE)
    user_ref = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    reason = models.TextField()
