using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Diagnostics;

namespace ANI.Models
{
    /// <summary>
    /// The AniContext class is a DbContext class that represents the database context for the application.
    /// Fields are defined for each entity set.
    /// </summary>
    /// <param name="options"></param>
    public class AniContext(DbContextOptions<AniContext> options) : DbContext(options)
    {
        /// <summary>
        /// Gets or sets the Users DbSet.
        /// </summary>
        public DbSet<User> Users { get; set; } = null!;

        /// <summary>
        /// Gets or sets the Products DbSet.
        /// </summary>
        public DbSet<Product> Products { get; set; } = null!;

        /// <summary>
        /// Gets or sets the Ratings DbSet.
        /// </summary>
        public DbSet<Rating> Ratings { get; set; } = null!;

        public DbSet<Order> Orders { get; set; } = null!;

        /// <summary>
        /// Configures the model that was discovered by convention from the entity types
        /// exposed in DbSet properties on your derived context.
        /// </summary>
        /// <param name="modelBuilder">The builder being used to construct the model for this context.</param>
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .HasIndex(u => u.Username)
                .IsUnique();

            modelBuilder.Entity<User>()
                .HasMany(u => u.Products)
                .WithOne(p => p.User)
                .HasForeignKey(p => p.UserID)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<User>()
                .Property(user => user.ProfilePictureUrl)
                .HasDefaultValue("Media/Images/Profiles/blank-profile-picture-973460_128012234212.png");

            modelBuilder.Entity<Product>()
                .Property(product => product.Description)
                .HasDefaultValue("The user has not provided a description for this product.");

            modelBuilder.Entity<Rating>()
               .HasOne(r => r.User)
               .WithMany()
               .HasForeignKey(r => r.UserID)
               .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Rating>()
                .HasOne(r => r.Product)
                .WithMany()
                .HasForeignKey(r => r.ProductID)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Order>()
               .HasOne(r => r.User)
               .WithMany()
               .HasForeignKey(r => r.UserID)
               .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Order>()
                .HasOne(r => r.Product)
                .WithMany()
                .HasForeignKey(r => r.ProductID)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<Order>()
                .Property(b => b.CreatedAt)
                .HasDefaultValueSql("CURRENT_TIMESTAMP");
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.LogTo(Console.WriteLine, [RelationalEventId.CommandExecuted]);
        }
    }
}