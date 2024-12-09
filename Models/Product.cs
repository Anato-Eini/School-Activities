using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ANI.Models;

public class Product
{
    [Key]
    public Guid ProductID { get; set; }
    public string Name { get; set; } = null!;
    public string Description { get; set; } = null!;
    public decimal Price { get; set; }
    public int Stock { get; set; }
    public string ProductPictureUrl { get; set; } = null!;
    public Guid UserID { get; set; }

    [ForeignKey("UserID")]
    public virtual User User { get; set; } = null!;
}