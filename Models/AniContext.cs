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
        public DbSet<User> Users { get; set; } = null!;


        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.LogTo(System.Console.WriteLine, [RelationalEventId.CommandExecuted]);
        }
    }

}