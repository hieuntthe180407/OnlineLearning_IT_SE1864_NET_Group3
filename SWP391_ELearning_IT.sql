Use [master]
Go
Create database [SWP391_FALL2024]
GO
USE [SWP391_FALL2024]
GO
/****** Object:  Table [dbo].[Blogs]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blogs](
	[BlogId] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NULL,
	[CategoryId] [int] NULL,
	[Title] [varchar](255) NOT NULL,
	[Content] [text] NOT NULL,
	[Status] [varchar](10) NULL,
	[FeaturedImage] [varchar](255) NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[BlogId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CategoriesBlog]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CategoriesBlog](
	[CategoryId] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [varchar](100) NOT NULL,
	[Description] [text] NULL,
	[CreatedAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Choices]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Choices](
	[ChoiceID] [int] IDENTITY(1,1) NOT NULL,
	[ExamQuestionID] [int] NOT NULL,
	[Description] [varchar](255) NULL,
	[IsTrue] [bit] NULL,
	[Comment] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[ChoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Course]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseID] [int] IDENTITY(1,1) NOT NULL,

	[CategoryID] [int] NOT NULL,
	[CourseImg] [varchar](255) NULL,
	[CourseName] [varchar](255) NOT NULL,
	[Publish] [bit] NULL,
	[Duration] [int] NULL,
	[Report] [int] NULL,
	[IsDiscontinued] [bit] NULL,
	[NewVersionId] [int] NULL,
	[Description] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[CourseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enroll]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enroll](
	[UserID] [int] NOT NULL,
	[CourseID] [int] NOT NULL,
	[Status] [varchar](50) NULL,
	[Progress] [int] NULL,
	[DateEnroll] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[CourseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Exam]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Exam](
	[ExamID] [int] IDENTITY(1,1) NOT NULL,
	[ExamName] [varchar](255) NOT NULL,
	[CourseID] [int] NOT NULL,
	[Duration] [int] NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ExamID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ExamDetail]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ExamDetail](
	[ExamDetailID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[ExamID] [int] NOT NULL,
	[TimeStart] [datetime] NULL,
	[TimeEnd] [datetime] NULL,
	[Status] [varchar](50) NULL,
	[Score] [decimal](5, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[ExamDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lessons]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lessons](
	[LessonID] [int] IDENTITY(1,1) NOT NULL,
	[LessonNumber] [int] NULL,
	[LessonName] [varchar](255) NOT NULL,
	[MoocID] [int] NOT NULL,
	[LessonUrl] [varchar](255) NULL,
	[Description] [text] NULL,
	[Status] [varchar](255) NOT NULL,
	[LessonImg] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LessonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mooc]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mooc](
	[MoocID] [int] IDENTITY(1,1) NOT NULL,
	[MoocNumber] [int] NULL,
	[MoocName] [varchar](255) NOT NULL,
	[CourseID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MoocID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Price]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Price](
	[PriceID] [int] IDENTITY(1,1) NOT NULL,
	[CourseID] [int] NOT NULL,
	[ListPrice] [decimal](10, 2) NOT NULL,
	[SalePrice] [decimal](10, 2) NULL,
	[IsActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[PriceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Purchase]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Purchase](
	[PurchaseID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[PriceID] [int] NOT NULL,
	[PurchaseDate] [datetime] NULL,
	[IsCompleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[PurchaseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[QuestionID] [int] IDENTITY(1,1) NOT NULL,
	[QuestionContent] [text] NOT NULL,
	[QuestionTitle] [text] NOT NULL,
	[QuestionType] NVARCHAR(255) NOT NULL CHECK (QuestionType IN ('Multiple Choice', 'Essay')),
	[QuestionImgOrVideo] NVARCHAR(255) NULL,
	[Level] NVARCHAR(10) CHECK (level IN ('Easy', 'Medium', 'Hard')) NOT NULL,
	[Status] NVARCHAR(10) CHECK (status IN ('Visible', 'Hidden')) NOT NULL DEFAULT 'Visible',
	[CourseID] [int] NOT NULL,
	[Explanation] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Review]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[ReviewID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[CourseID] [int] NOT NULL,
	[Rating] [int] NULL,
	[Time] [datetime] NULL,
	[ReviewContent] [text] NULL,
	
PRIMARY KEY CLUSTERED 
(
	[ReviewID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Role]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Slider](
	[SliderID] [int] IDENTITY(1,1) NOT NULL,
	[ImageUrl] [nvarchar](255) NULL,
	[Title] [nvarchar](255) NULL,
	[BackLink] [nvarchar](255) NULL,
	[Description] [nvarchar](255) NULL,
	[Publish] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[SliderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]	 

/****** Object:  Table [dbo].[User]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FullName] [varchar](255) NOT NULL,
	[DateOfBirth] [date] NULL,
	[Email] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[Phone] [varchar](20) NULL,
	[Address] [varchar](255) NULL,
	[Gender] [varchar](10) NULL,
	[RoleID] [int] NULL,
	[About] [text] NULL,
	
	[Avatar] [varchar](255) NULL,
	[Status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserChoices]    Script Date: 9/14/2024 10:13:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserChoices](
	[UserChoiceID] [int] IDENTITY(1,1) NOT NULL,
	[ExamDetailID] [int] NOT NULL,
	[ChoiceID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserChoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Exam_Question] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Exam_Question](
	[ExamQuestionID] [int] NOT NULL,
	[QuestionID] [int] NOT NULL,
	[ExamID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ExamQuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Answer]  ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Answer](
    [AnswerID] [int] IDENTITY(1,1) NOT NULL,
    [QuestionID] [int] NOT NULL,
    [OptionContent] [varchar](255) NOT NULL,
    [IsCorrect] [bit] NOT NULL DEFAULT 0,
PRIMARY KEY CLUSTERED 
(
    [AnswerID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Answer]  WITH CHECK ADD FOREIGN KEY([QuestionID])
REFERENCES [dbo].[Question] ([QuestionID])
GO

ALTER TABLE [dbo].[Exam_Question]  WITH CHECK ADD FOREIGN KEY([QuestionID])
REFERENCES [dbo].[Question] ([QuestionID])
GO
ALTER TABLE [dbo].[Exam_Question]  WITH CHECK ADD FOREIGN KEY([ExamID])
REFERENCES [dbo].[Exam] ([ExamID])
GO


ALTER TABLE [dbo].[Course] ADD  DEFAULT ((1)) FOR [Publish]
GO
ALTER TABLE [dbo].[Course] ADD  DEFAULT ((0)) FOR [Report]
GO
ALTER TABLE [dbo].[Course] ADD  DEFAULT ((0)) FOR [IsDiscontinued]
GO
ALTER TABLE [dbo].[Enroll] ADD  DEFAULT ((0)) FOR [Progress]
GO

ALTER TABLE [dbo].[Price] ADD  DEFAULT ((1)) FOR [IsActive]
GO

ALTER TABLE [dbo].[Purchase] ADD  DEFAULT (getdate()) FOR [PurchaseDate]
GO

ALTER TABLE [dbo].[User] ADD  DEFAULT (('Active')) FOR [Status]
GO

ALTER TABLE [dbo].[Review] ADD  DEFAULT (getdate()) FOR [Time]
GO
ALTER TABLE [dbo].[Lessons] ADD  DEFAULT (('Active')) FOR [Status]
GO


ALTER TABLE [dbo].[Blogs]  WITH CHECK ADD FOREIGN KEY([CategoryId])
REFERENCES [dbo].[CategoriesBlog] ([CategoryId])
GO
ALTER TABLE [dbo].[Blogs]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([UserID])
GO

ALTER TABLE [dbo].[Choices]  WITH CHECK ADD FOREIGN KEY([ExamQuestionID])
REFERENCES [dbo].[Exam_Question] ([ExamQuestionID])
GO


ALTER TABLE [dbo].[Course]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO

ALTER TABLE [dbo].[Enroll]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
GO
ALTER TABLE [dbo].[Enroll]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Exam]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
GO
ALTER TABLE [dbo].[ExamDetail]  WITH CHECK ADD FOREIGN KEY([ExamID])
REFERENCES [dbo].[Exam] ([ExamID])
GO
ALTER TABLE [dbo].[ExamDetail]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Lessons]  WITH CHECK ADD FOREIGN KEY([MoocID])
REFERENCES [dbo].[Mooc] ([MoocID])
GO
ALTER TABLE [dbo].[Mooc]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
GO
ALTER TABLE [dbo].[Price]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD FOREIGN KEY([PriceID])
REFERENCES [dbo].[Price] ([PriceID])
GO
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
GO

GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO



ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[UserChoices]  WITH CHECK ADD FOREIGN KEY([ChoiceID])
REFERENCES [dbo].[Choices] ([ChoiceID])
GO
ALTER TABLE [dbo].[UserChoices]  WITH CHECK ADD FOREIGN KEY([ExamDetailID])
REFERENCES [dbo].[ExamDetail] ([ExamDetailID])
GO

ALTER TABLE [dbo].[Review]  WITH CHECK ADD CHECK  (([Rating]>=(1) AND [Rating]<=(5)))
GO

ALTER TABLE [dbo].[Category]
ADD [Image] NVARCHAR(MAX);  

ALTER TABLE [dbo].[Course]
ADD UserID INT NULL;  

ALTER TABLE [dbo].[Course]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO

CREATE TABLE [dbo].[GuestPhone](
	[PhoneID] [int] IDENTITY(1,1) NOT NULL,
	[GuestId] [int] NOT NULL,
	
	[PhoneNum] [varchar](255) NOT NULL
	PRIMARY KEY CLUSTERED 
(
	[PhoneID] ASC
)
	)
	


CREATE TABLE [dbo].[Guest](
	[GuestId] [int] IDENTITY(1,1) NOT NULL,
	[PreferredPhone] [varchar](255) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[FullName] [varchar](255) NOT NULL,
	[Address] [varchar](255) NOT NULL
	PRIMARY KEY CLUSTERED 
(
	[GuestId] ASC
)
	)
	ALTER TABLE Purchase
		ADD GuestId int NULl;
		ALTER TABLE Purchase
		ALTER COLUMN UserID int  NULL;
	ALTER TABLE [dbo].[GuestPhone]  WITH CHECK ADD FOREIGN KEY([GuestId])
REFERENCES [dbo].[Guest] ([GuestId])
ALTER TABLE [dbo].[Purchase]  WITH CHECK ADD FOREIGN KEY([GuestId])
REFERENCES [dbo].[Guest] ([GuestId])

Alter Table Enroll
Alter column DateEnroll datetime
ALTER TABLE [dbo].[Enroll] ADD  DEFAULT (getdate()) FOR [DateEnroll]


SET IDENTITY_INSERT [dbo].[Guest] ON 

INSERT [dbo].[Guest] ([GuestId], [PreferredPhone], [Email], [FullName], [Address]) VALUES (1, N'0968669718', N'TRONGHIEUTRONGHIEU11@GMAIL.COM', N'Hieu Nguyen', N'con cuc')
SET IDENTITY_INSERT [dbo].[Guest] OFF
GO
SET IDENTITY_INSERT [dbo].[GuestPhone] ON 

INSERT [dbo].[GuestPhone] ([PhoneID], [GuestId], [PhoneNum]) VALUES (1, 1, N'0968669718')
INSERT [dbo].[GuestPhone] ([PhoneID], [GuestId], [PhoneNum]) VALUES (2, 1, N'0999999999')
SET IDENTITY_INSERT [dbo].[GuestPhone] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Image]) VALUES (1, N'Front End', N'https://vtiacademy.edu.vn/upload/images/front-end-hoc-gi-nhung-ky-nang-can-thiet-cho-lap-trinh-vien-front-end-.png')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Image]) VALUES (2, N'Back End', N'https://mikotech.vn/wp-content/uploads/2023/06/back-end-la-gi-2.png')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (1, N'User')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (2, N'Teacher')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (3, N'Admin')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (1, N'Alice Johnson', CAST(N'1985-02-15' AS Date), N'alice.johnson@example.com', N'password123', N'555-1234', N'123 Elm Street', N'Female', 1, NULL, N'avatar1.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (2, N'Bob Smith', CAST(N'1990-07-10' AS Date), N'bob.smith@example.com', N'password123', N'555-5678', N'456 Oak Avenue', N'Male', 2, N'Experienced project manager.', N'https://upload.wikimedia.org/wikipedia/commons/1/1a/Faker_2020_interview.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (3, N'Carol White', CAST(N'1978-11-23' AS Date), N'carol.white@example.com', N'password123', N'555-8765', N'789 Pine Road', N'Female', 3, NULL, N'avatar3.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (4, N'David Brown', CAST(N'1989-05-12' AS Date), N'david.brown@example.com', N'password123', N'555-1357', N'135 Cedar Drive', N'Male', 1, NULL, N'avatar4.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (5, N'Emma Green', CAST(N'1992-12-20' AS Date), N'emma.green@example.com', N'password123', N'555-2468', N'246 Birch Lane', N'Female', 2, N'Professional photographer and designer.', N'avatar5.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (6, N'Frank Black', CAST(N'1983-04-05' AS Date), N'frank.black@example.com', N'password123', N'555-3698', N'369 Maple Avenue', N'Male', 3, NULL, N'avatar6.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (7, N'Grace Hill', CAST(N'1987-09-30' AS Date), N'grace.hill@example.com', N'password123', N'555-4321', N'432 Willow Way', N'Female', 2, N'Marketing specialist with 5+ years experience.', N'avatar7.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (8, N'Harry King', CAST(N'1995-01-11' AS Date), N'harry.king@example.com', N'password123', N'555-5670', N'567 Ash Street', N'Male', 1, NULL, N'avatar8.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (9, N'Ivy Adams', CAST(N'1991-08-29' AS Date), N'ivy.adams@example.com', N'password123', N'555-7890', N'789 Chestnut Blvd', N'Female', 3, NULL, N'avatar9.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (10, N'Jack Reed', CAST(N'1984-10-15' AS Date), N'jack.reed@example.com', N'password123', N'555-1112', N'111 Cedar Street', N'Male', 2, N'Software developer specializing in web applications.', N'avatar10.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (11, N'Kate Lee', CAST(N'1990-03-18' AS Date), N'kate.lee@example.com', N'password123', N'555-2223', N'222 Walnut Lane', N'Female', 1, NULL, N'avatar11.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (12, N'Liam Scott', CAST(N'1979-07-25' AS Date), N'liam.scott@example.com', N'password123', N'555-3334', N'333 Fir Drive', N'Male', 2, N'IT consultant and data analyst.', N'avatar12.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (13, N'Mia Turner', CAST(N'1985-11-10' AS Date), N'mia.turner@example.com', N'password123', N'555-4445', N'444 Spruce Road', N'Female', 3, NULL, N'avatar13.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (14, N'Noah Perez', CAST(N'1993-05-21' AS Date), N'noah.perez@example.com', N'password123', N'555-5556', N'555 Hemlock Avenue', N'Male', 1, NULL, N'avatar14.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (15, N'Olivia Ross', CAST(N'1997-09-14' AS Date), N'olivia.ross@example.com', N'password123', N'555-6667', N'666 Beech Blvd', N'Female', 2, N'Customer support specialist.', N'avatar15.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (16, N'Paul Young', CAST(N'1988-12-02' AS Date), N'paul.young@example.com', N'password123', N'555-7778', N'777 Hickory Way', N'Male', 3, NULL, N'avatar16.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (17, N'Quinn Price', CAST(N'1994-06-08' AS Date), N'quinn.price@example.com', N'password123', N'555-8889', N'888 Alder Street', N'Female', 2, N'Sales and business development expert.', N'avatar17.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (18, N'Rachel Brooks', CAST(N'1986-03-04' AS Date), N'rachel.brooks@example.com', N'password123', N'555-9990', N'999 Poplar Ave', N'Female', 1, NULL, N'avatar18.jpg', N'Inactive')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (19, N'Sam Fisher', CAST(N'1992-10-20' AS Date), N'sam.fisher@example.com', N'password123', N'555-0001', N'000 Red Maple Street', N'Male', 2, N'Logistics manager with international experience.', N'avatar19.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (20, N'Tina Sanders', CAST(N'1990-02-28' AS Date), N'tina.sanders@example.com', N'password123', N'555-1012', N'101 Sycamore Circle', N'Female', 3, NULL, N'avatar20.jpg', N'Active')
INSERT [dbo].[User] ([UserID], [FullName], [DateOfBirth], [Email], [Password], [Phone], [Address], [Gender], [RoleID], [About], [Avatar], [Status]) VALUES (21, N'Hieu Nguyen', CAST(N'2004-10-15' AS Date), N'TRONGHIEUTRONGHIEU11@GMAIL.COM', N'123', N'0968669718', N'gg', N'Male', 1, NULL, N'imgavatar/default-avatar.jpg', N'Active')
SET IDENTITY_INSERT [dbo].[User] OFF
GO
SET IDENTITY_INSERT [dbo].[Course] ON 

INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (2, 1, N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRytZMLvKNFPl0WXSpXmfeM7c_qrlrI6l2Jxw&s', N'Introduction to Data Science', 1, 30, 100, 0, NULL, N'This is a beginner course on Data Science.', 2)
INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (14, 1, N'img/Course/html.jpg', N'HTML', 1, 0, NULL, 0, NULL, N'HTML (HyperText Markup Language) is the standard language used to create and structure content on the web. It uses elements, tags, and attributes to define headings, paragraphs, images, links, forms, and more, forming the backbone of most web pages. HTML works alongside CSS and JavaScript to create dynamic, styled, and interactive websites.', 2)
INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (15, 1, N'imageStorage\course\csss.jpg', N'CSS', 1, 0, NULL, 0, NULL, N'CSS (Cascading Style Sheets) is a language used to style and layout web pages. It controls the appearance of HTML elements, including colors, fonts, spacing, positioning, and responsiveness. CSS enables web designers to create visually appealing, consistent designs across multiple pages and devices by separating content (HTML) from presentation (CSS).', 2)
INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (16, 1, N'imageStorage\course\javascript.png', N'Javascript', 0, 0, NULL, 0, NULL, N'JavaScript is a versatile programming language used to add interactivity, animations, and dynamic content to web pages. It runs in the browser, allowing developers to create responsive user interfaces, control multimedia, handle events, and interact with servers through APIs. JavaScript works alongside HTML and CSS, making it a core technology for creating engaging, interactive web experiences.', 2)
INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (17, 2, N'imageStorage\course\java.png', N'Java ', 0, 0, NULL, 0, NULL, N'Java is a high-level, object-oriented programming language widely used for building cross-platform applications. Known for its "write once, run anywhere" capability, Java runs on the Java Virtual Machine (JVM), allowing code to execute consistently across different operating systems. Java is popular for web development, mobile applications (especially Android), enterprise systems, and more, thanks to its stability, scalability, and vast ecosystem of libraries and frameworks.', 2)
INSERT [dbo].[Course] ([CourseID], [CategoryID], [CourseImg], [CourseName], [Publish], [Duration], [Report], [IsDiscontinued], [NewVersionId], [Description], [UserID]) VALUES (20, 1, N'img/Course/csharp.jpg', N'C#', 0, 0, NULL, 0, NULL, N'C# (C-sharp) is a modern, object-oriented programming language developed by Microsoft, commonly used for building Windows applications, web applications, and games with the Unity engine. Running on the .NET framework, C# offers robust features like strong typing, garbage collection, and support for asynchronous programming. Its syntax is similar to other C-style languages, making it accessible and versatile for developers creating scalable and performance-driven applications across platforms.
', 2)
SET IDENTITY_INSERT [dbo].[Course] OFF
GO
SET IDENTITY_INSERT [dbo].[Price] ON 

INSERT [dbo].[Price] ([PriceID], [CourseID], [ListPrice], [SalePrice], [IsActive]) VALUES (3, 2, CAST(300.00 AS Decimal(10, 2)), CAST(200.00 AS Decimal(10, 2)), 1)
SET IDENTITY_INSERT [dbo].[Price] OFF
GO
SET IDENTITY_INSERT [dbo].[Purchase] ON 

INSERT [dbo].[Purchase] ([PurchaseID], [UserID], [PriceID], [PurchaseDate], [IsCompleted], [GuestId]) VALUES (1, 21, 3, CAST(N'2024-11-08T01:35:55.037' AS DateTime), NULL, NULL)
INSERT [dbo].[Purchase] ([PurchaseID], [UserID], [PriceID], [PurchaseDate], [IsCompleted], [GuestId]) VALUES (2, NULL, 3, CAST(N'2024-11-09T13:00:19.827' AS DateTime), NULL, 1)
SET IDENTITY_INSERT [dbo].[Purchase] OFF
GO
INSERT [dbo].[Enroll] ([UserID], [CourseID], [Status], [Progress], [DateEnroll]) VALUES (21, 2, NULL, 0, CAST(N'2024-11-08T01:35:55.073' AS DateTime))
INSERT [dbo].[Enroll] ([UserID], [CourseID], [Status], [Progress], [DateEnroll]) VALUES (21, 14, NULL, 0, CAST(N'2024-11-10T00:06:31.117' AS DateTime))
INSERT [dbo].[Enroll] ([UserID], [CourseID], [Status], [Progress], [DateEnroll]) VALUES (21, 15, NULL, 0, CAST(N'2024-11-10T00:06:43.160' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Mooc] ON 

INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (2, 101, N'Introduction to Programming11', 2)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (5, 102, N'bruh', 2)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (6, 1, N'Introduction to HTML', 14)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (10, 2, N'HTML Elements and Attributes', 14)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (11, 3, N'HTML5 Features', 14)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (12, 1, N'Introduction to CSS', 15)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (13, 2, N'CSS Styling', 15)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (14, 3, N'Responsive Design', 15)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (15, 1, N'Introduction to JavaScript', 16)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (16, 2, N'DOM Manipulation', 16)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (17, 3, N'JavaScript Frameworks', 16)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (18, 4, N'Object-Oriented JavaScript', 16)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (19, 1, N'Introduction to Java ', 17)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (20, 2, N'Object-Oriented Programming (OOP):', 17)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (21, 3, N'Collections Framework', 17)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (22, 1, N'Introduction to C#', 20)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (23, 2, N'Advanced C# Concepts', 20)
INSERT [dbo].[Mooc] ([MoocID], [MoocNumber], [MoocName], [CourseID]) VALUES (24, 3, N'Windows Application Development', 20)
SET IDENTITY_INSERT [dbo].[Mooc] OFF
GO
SET IDENTITY_INSERT [dbo].[Lessons] ON 

INSERT [dbo].[Lessons] ([LessonID], [LessonNumber], [LessonName], [MoocID], [LessonUrl], [Description], [Status], [LessonImg]) VALUES (11, 1, N'Getting Started with Programming', 2, N'https://www.youtube.com/watch?v=ZnVwQ6vD3HY', N'An introductory lesson on programming basics.', N'Active', N'https://media.licdn.com/dms/image/D5612AQE09OjaLfBmfw/article-cover_image-shrink_720_1280/0/1717488548802?e=2147483647&v=beta&t=Dea9j_u_ak0Vc0nQsQHtk0Y8QFeCTq-GqtCk8gluU1Y')
INSERT [dbo].[Lessons] ([LessonID], [LessonNumber], [LessonName], [MoocID], [LessonUrl], [Description], [Status], [LessonImg]) VALUES (12, 2, N'Understanding OOP Concepts', 2, N'http://example.com/lesson2', N'Lesson on Object-Oriented Programming concepts.', N'Active', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJj_tgz6-MXJHSqWvB6_FpwpGxKBh6n7j6aw&s')
INSERT [dbo].[Lessons] ([LessonID], [LessonNumber], [LessonName], [MoocID], [LessonUrl], [Description], [Status], [LessonImg]) VALUES (19, 1, N'getting started with programming', 2, N'https://www.youtube.com/watch?v=66tfvFeALBQ', N'This introductory lesson covers the basics of programming, ideal for beginners with no prior experience. You will learn essential concepts such as:

What Programming Is: Understand the purpose of programming, how it powers software, and why it''s a valuable skill.
Programming Languages: Get an overview of popular languages like Python, Java, and JavaScript, along with their unique uses.
Writing Your First Code: Begin with simple, hands-on examples to write and execute basic code.
Logic and Problem-Solving: Explore the core logic behind programming, including algorithms and step-by-step problem-solving techniques.', N'Active', N'https://cdnphoto.dantri.com.vn/MqUVuRtIdoo7LlU9oTTM1qybuMU=/2023/10/01/004834524797835225919c73401k166971614833-1696176422301.jpg')
INSERT [dbo].[Lessons] ([LessonID], [LessonNumber], [LessonName], [MoocID], [LessonUrl], [Description], [Status], [LessonImg]) VALUES (20, 1, N'What is HTML?', 6, N'https://vietnix.vn/html-la-gi/?psafe_param=1&gad_source=1&gclid=Cj0KCQiArby5BhCDARIsAIJvjIR_iB84jCxX5IOdiEDV1knKTxDrGoz2LRTge4CW6FkYaf36X9eV55QaAtYJEALw_wcB', N'What is HTML? Basic platform for beginner', N'Active', N'https://static-xf1.vietnix.vn/wp-content/uploads/2023/11/HTML-la-gi.webp')
INSERT [dbo].[Lessons] ([LessonID], [LessonNumber], [LessonName], [MoocID], [LessonUrl], [Description], [Status], [LessonImg]) VALUES (28, 2, N'Understanding HTML elements', 10, N'https://blog.hubspot.com/website/html-elements', N'Tags, attributes, and elements are fundamental for creating structured and well-formed HTML documents. They are the building blocks of a web page.', N'Active', N'https://codefinity-content-media-v2.s3.eu-west-1.amazonaws.com/courses/392f24ac-0126-4c34-a28a-f2f26d12196b/section-1/tag%2C+attribute%2C+element+visualisation.png')
SET IDENTITY_INSERT [dbo].[Lessons] OFF
GO
