using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using Microsoft.Extensions.Logging.Abstractions;

namespace ANI.Models
{
    public class Rating
    {
        [Key]
        public Guid RatingID { get; set; }
        public string Name { get; set; } = null!;
        public string Content { get; set; } = null!;
        public int Stars { get; set; }
        public Guid ProductID { get; set; }
        
        public Guid UserID { get; set; }

        [ForeignKey("UserID")]
        public virtual User User { get; set; } = null!;

        [ForeignKey("ProductID")]
        public virtual Product Product { get; set; } = null!;
    }
}
