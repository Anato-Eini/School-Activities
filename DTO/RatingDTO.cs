namespace ANI.DTO;

public class RatingDTO
{
    public Guid RatingID { get; set; }
    public string Name { get; set; } = null!;
    public string Content { get; set; } = null!;
    public int Stars { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
}