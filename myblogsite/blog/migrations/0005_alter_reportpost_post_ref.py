# Generated by Django 5.1.2 on 2024-11-25 02:03

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('blog', '0004_reportcomment_user_ref_reportpost_user_ref'),
    ]

    operations = [
        migrations.AlterField(
            model_name='reportpost',
            name='post_ref',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='blog.userpost'),
        ),
    ]
